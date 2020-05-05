package me.oktop.springbootrabbitmq.controller;

import lombok.RequiredArgsConstructor;
import me.oktop.springbootrabbitmq.service.RabbitMQService;
import me.oktop.springbootrabbitmq.web.vo.DeviceVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQService rabbitMQService;

    @PostMapping("/send")
    public String publish(@RequestBody final DeviceVo vo) {
        final String queueName = "dynamicQueue";
        rabbitMQService.dynamicQueueCreationAndMessagePublish(queueName, vo);
        return "success";
    }
}
