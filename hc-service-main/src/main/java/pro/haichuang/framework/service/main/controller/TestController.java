package pro.haichuang.framework.service.main.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.haichuang.framework.base.enums.base.SexEnum;
import pro.haichuang.framework.service.main.dto.TestDTO;

/**
 * TestController
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Api(tags = "[Test] 测试控制器")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("枚举序列化测试")
    @PostMapping("/enum")
    public Object enumTest(SexEnum sex, String a, @RequestBody TestDTO testDTO) {
        return JSONObject.toJSONString(testDTO) + sex;
    }
}
