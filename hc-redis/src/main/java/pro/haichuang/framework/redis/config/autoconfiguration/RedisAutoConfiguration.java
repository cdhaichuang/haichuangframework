package pro.haichuang.framework.redis.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.redis.config.RedisConfig;
import pro.haichuang.framework.redis.config.properties.RedisProperties;

/**
 * Redis自动配置
 * 标注 {@link AutoConfigureBefore} 注解后会在指定自动配置类前加载
 *
 * @author JiYinchuan
 * @version 1.0
 * @see org.springframework.boot.autoconfigure.AutoConfigureOrder
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(
        prefix = "haichuang.redis",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true
)
@Import({
        RedisConfig.class
})
public class RedisAutoConfiguration {
}
