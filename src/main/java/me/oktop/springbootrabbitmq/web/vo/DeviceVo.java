package me.oktop.springbootrabbitmq.web.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class DeviceVo extends DeviceCommonVo{

    private String dataId;

    private List<Map<String, Object>> status;

    @Builder
    public DeviceVo(String dataId,
                              String deviceId,
                              String productKey,
                              List<Map<String, Object>> status) {
        super(deviceId, productKey);
        this.dataId = dataId;
        this.status = status;
    }

}
