package me.oktop.springbootrabbitmq.web.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class CommandVo extends DeviceCommonVo {

    private String code;

    private Map<String, Object> data;

    private Long ts;

    @Builder
    public CommandVo(String code,
                     Map<String, Object> data,
                     Long ts,
                     String deviceId,
                     String productKey) {
        super(deviceId, productKey);
        this.code = code;
        this.data = data;
        this.ts = ts;
    }

}
