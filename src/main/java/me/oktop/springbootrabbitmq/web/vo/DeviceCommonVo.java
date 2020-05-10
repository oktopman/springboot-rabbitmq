package me.oktop.springbootrabbitmq.web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class DeviceCommonVo {

    @NotBlank
    @JsonProperty("devId")
    private String deviceId;

    private String productKey;

    public DeviceCommonVo(String deviceId, String productKey) {
        this.deviceId = deviceId;
        this.productKey = productKey;
    }

}
