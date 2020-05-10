package me.oktop.springbootrabbitmq.service;

import me.oktop.springbootrabbitmq.web.vo.DeviceVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RabbitMQServiceTest {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Test
    @DisplayName("동적 큐 생성 테스트")
    void create_dynamic_queue_test() throws IllegalAccessException {
        //given
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1.put("code", "movement_detect_pic");
        map1.put("t", "1588746308854");
        map1.put("115", "eyJ2IjoiMy4wIiwiYnVja2V0IjoidHktdXMtc3RvcmFnZTMwIiwiZmlsZXMiOltbIi80Y2FlOTAtMTQ1NTQwMDEtcHAwMTE1YTc5MDg0ZTQzODQyMmMvZGV0ZWN0LzE1ODg3NDYzMDcuanBlZyIsIjRkYWUzNjI4ZjhmM2FiZDQiXV19");
        map1.put("value", "eyJ2IjoiMy4wIiwiYnVja2V0IjoidHktdXMtc3RvcmFnZTMwIiwiZmlsZXMiOltbIi80Y2FlOTAtMTQ1NTQwMDEtcHAwMTE1YTc5MDg0ZTQzODQyMmMvZGV0ZWN0LzE1ODg3NDYzMDcuanBlZyIsIjRkYWUzNjI4ZjhmM2FiZDQiXV19");

        map2.put("code", "movement_detect_pic");
        map2.put("t", "1588746308854");
        map2.put("115", "eyJ2IjoiMy4wIiwiYnVja2V0IjoidHktdXMtc3RvcmFnZTMwIiwiZmlsZXMiOltbIi80Y2FlOTAtMTQ1NTQwMDEtcHAwMTE1YTc5MDg0ZTQzODQyMmMvZGV0ZWN0LzE1ODg3NDYzMDcuanBlZyIsIjRkYWUzNjI4ZjhmM2FiZDQiXV19");
        map2.put("value", "eyJ2IjoiMy4wIiwiYnVja2V0IjoidHktdXMtc3RvcmFnZTMwIiwiZmlsZXMiOltbIi80Y2FlOTAtMTQ1NTQwMDEtcHAwMTE1YTc5MDg0ZTQzODQyMmMvZGV0ZWN0LzE1ODg3NDYzMDcuanBlZyIsIjRkYWUzNjI4ZjhmM2FiZDQiXV19");
        list.add(map1);
        list.add(map2);

        DeviceVo vo = DeviceVo.builder()
                .dataId("0005A4F4D360A0626CADDD0139850077")
                .deviceId("eb547a20edf3d986c3vc07")
                .productKey("6acl3uwsvxlbpmxh")
                .status(list)
                .build();
        //when
//        for (int i = 0; i < 5; i++) {
        rabbitMQService.messagePublish(vo);
//        }
    }


}