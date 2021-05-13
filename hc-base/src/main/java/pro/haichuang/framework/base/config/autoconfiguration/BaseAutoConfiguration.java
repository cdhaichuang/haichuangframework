package pro.haichuang.framework.base.config.autoconfiguration;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.mvc.JacksonConfig;
import pro.haichuang.framework.base.config.mvc.WebMvcConfig;
import pro.haichuang.framework.base.config.properties.BaseConfigProperties;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * 配置自动配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(BaseConfigProperties.class)
@ConditionalOnProperty(
        prefix = "haichuang.config",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true
)
@Import({
        WebMvcConfig.class,
        JacksonConfig.class,
        FastJsonConfig.class
})
public class BaseAutoConfiguration {

}
