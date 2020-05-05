package me.oktop.springbootrabbitmq.web.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeviceVo {

    private String user;
    private String name;

    @Builder
    public DeviceVo(String user, String name) {
        this.user = user;
        this.name = name;
    }
}
