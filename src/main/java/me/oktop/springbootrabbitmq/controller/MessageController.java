package me.oktop.springbootrabbitmq.controller;

import lombok.RequiredArgsConstructor;
import me.oktop.springbootrabbitmq.service.RabbitMQService;
import me.oktop.springbootrabbitmq.web.vo.CommandVo;
import me.oktop.springbootrabbitmq.web.vo.DeviceCommonVo;
import me.oktop.springbootrabbitmq.web.vo.DeviceVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQService<DeviceCommonVo> rabbitMQService;

    @PostMapping("/send")
    public String publish(@Valid @RequestBody final DeviceVo vo) throws IllegalAccessException {
        rabbitMQService.messagePublish(vo);
        return "success";
    }

    @PostMapping("/send2")
    public String publish2(@Valid @RequestBody final CommandVo vo) throws IllegalAccessException {
        rabbitMQService.messagePublish(vo);
        return "success";
    }
}
