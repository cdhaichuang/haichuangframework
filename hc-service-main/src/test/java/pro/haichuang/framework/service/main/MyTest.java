package pro.haichuang.framework.service.main;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.haichuang.framework.base.enums.abnormal.RegisterAbnormalEnum;
import pro.haichuang.framework.base.enums.base.SexEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * MyTest
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SpringBootTest(classes = ServiceApplication.class)
public class MyTest {

    @Test
    void dateTest() {
        System.out.println(new JSONObject()
                // .fluentPut("LocalDateTime", LocalDateTime.now())
                // .fluentPut("LocalDate", LocalDate.now())
                // .fluentPut("LocalTime", LocalTime.now())
                // .fluentPut("LocalTime", LocalTime.now())
                // .fluentPut("Date", new Date())
                .fluentPut("123", RegisterAbnormalEnum.REGISTER_ERROR)
                .toJSONString());
        // System.out.println(JSONObject.parseObject(JSONObject.toJSONString(SexEnum.MAN), SexEnum.class));
        // System.out.println(JSONObject.parseObject(JSONObject.toJSONString(RegisterAbnormalEnum.REGISTER_ERROR), RegisterAbnormalEnum.class));
    }
}
