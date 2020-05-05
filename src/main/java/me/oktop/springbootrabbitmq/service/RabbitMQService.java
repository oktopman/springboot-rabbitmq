package me.oktop.springbootrabbitmq.service;

import lombok.RequiredArgsConstructor;
import me.oktop.springbootrabbitmq.web.vo.DeviceVo;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RabbitMQService {
    private static final String topicExchangeName = "spring-boot-exchange";
    private static final String ROUTING_KEY = "route";

    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    public void messagePublish() {
        Map<String, String> map = new HashMap<>();
        map.put("user", "user");
        map.put("name", "name");
        rabbitTemplate.convertAndSend(topicExchangeName, ROUTING_KEY, map);
    }

//    public void dynamicQueueCreationAndMessagePublish(String queueName, Map<String, String> map) {
    public void dynamicQueueCreationAndMessagePublish(String queueName, DeviceVo vo) {
        String dynamicQueue = queueName;
        boolean isTrue = validateCreationQueue(queueName);
        Map<String, String> queueMap = new HashMap<>();
        if (isTrue) {
            queueMap.put("exchange", dynamicQueue + "Exchange");
            queueMap.put("routingKey", ROUTING_KEY);
        } else {
            Map<String, Object> args = new HashMap<>();
            args.put("x-message-ttl", 20000);
            Queue queue = new Queue(dynamicQueue, false, false, false, args);
            TopicExchange exchange = new TopicExchange(dynamicQueue + "Exchange");
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY));
            queueMap.put("exchange", exchange.getName());
            queueMap.put("routingKey", ROUTING_KEY);
        }

        rabbitTemplate.convertAndSend(queueMap.get("exchange"), queueMap.get("routingKey"), vo);
    }

    private boolean validateCreationQueue(String queueName) {
        QueueInformation queueInfo = amqpAdmin.getQueueInfo(queueName);
        return queueInfo != null;
    }

}
