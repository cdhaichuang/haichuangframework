package pro.haichuang.framework.base.config.autoconfiguration;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.mvc.JacksonConfig;
import pro.haichuang.framework.base.config.mvc.WebMvcConfig;
import pro.haichuang.framework.base.config.properties.BaseConfigProperties;

/**
 * 核心自动配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(BaseConfigProperties.class)
@ConditionalOnProperty(
        prefix = "haichuang",
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
