package me.oktop.springbootrabbitmq.service;

import me.oktop.springbootrabbitmq.web.vo.DeviceVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RabbitMQServiceTest {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    void 동적큐생성후_메세지퍼블리쉬_테스트() {
        String queueName = "dynamicQueue";
        DeviceVo vo = DeviceVo.builder()
                .user("hayun")
                .name("leehayun")
                .build();

        rabbitMQService.dynamicQueueCreationAndMessagePublish(queueName, vo);
    }

    @Test
    void 큐리스트_조회_테스트() {
        String queueName = "dynamicQueue";
        QueueInformation queueInfo = amqpAdmin.getQueueInfo(queueName);
        if (queueInfo != null) {
            System.out.println("Not Null");
        } else {
            System.out.println("Null");
        }
    }

}