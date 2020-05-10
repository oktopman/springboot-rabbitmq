package me.oktop.springbootrabbitmq.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.oktop.springbootrabbitmq.web.vo.DeviceCommonVo;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQService <T extends DeviceCommonVo> {
    private static final String TOPIC_EXCHANGE_NAME = "topicExchangeName";
    private static final String ROUTING_KEY = "routingKey";

    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    public void messagePublish(T vo) throws IllegalAccessException {
        if (StringUtils.isEmpty(vo.getDeviceId())) {
            log.error("DeviceId is Null Or Empty !!");
            throw new IllegalAccessException("DeviceId is Null Or Empty !!");
        }
        String clientId = vo.getDeviceId();

        Map<String, String> queueMap = this.createMessageForSendToQueue(clientId);
        rabbitTemplate.convertAndSend(queueMap.get(TOPIC_EXCHANGE_NAME), queueMap.get(ROUTING_KEY), vo);
    }

    private Map<String, String> createMessageForSendToQueue(String clientId) {
        final String exchangeName = clientId + "Exchange";
        final String routingKey = clientId + "Route";

        Map<String, String> queueMap = new HashMap<>();
        queueMap.put(TOPIC_EXCHANGE_NAME, exchangeName);
        queueMap.put(ROUTING_KEY, routingKey);

        if (this.ValidateExistsQueue(clientId)) {
            return queueMap;
        }

        this.createQueue(clientId);
        return queueMap;
    }


    private boolean ValidateExistsQueue(String clientId) {
        QueueInformation queueInfo = amqpAdmin.getQueueInfo(clientId);
        return queueInfo != null;
    }

    private void createQueue(String clientId) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 30000);
        // durable = true 선언시, rabbitmq 서버가 죽거나 restart할때 메세지 유실을 방지
        Queue queue = new Queue(clientId, false, false, false, args);
        TopicExchange directExchange = new TopicExchange(clientId + "Exchange");
        String routingKey = queue.getName() + "Route";
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routingKey));
    }


}
