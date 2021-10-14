package pro.haichuang.framework.base.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.mvc.filter.RepeatAndMdcFilter;
import pro.haichuang.framework.base.config.mvc.FastJsonConfig;
import pro.haichuang.framework.base.config.mvc.JacksonConfig;
import pro.haichuang.framework.base.config.mvc.WebMvcConfig;
import pro.haichuang.framework.base.config.properties.BaseConfigProperties;

/**
 * 核心自动配置
 *
 * <p>该类为 {@code hc-base} 模块核心自动配置类
 * <p>可以在 {@code yaml} 配置文件中指定 {@link BaseConfigProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see WebMvcConfig
 * @see JacksonConfig
 * @see FastJsonConfig
 * @since 1.0.0.211009
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
        RepeatAndMdcFilter.class,
        WebMvcConfig.class,
        JacksonConfig.class,
        FastJsonConfig.class
})
public class BaseAutoConfiguration {
}
