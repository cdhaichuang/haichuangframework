package pro.haichuang.framework.redis.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.redis.config.RedisConfig;
import pro.haichuang.framework.redis.config.properties.RedisProperties;

/**
 * Redis自动配置
 *
 * <p>该类为 {@code hc-redis} 模块核心自动配置类
 * <p>可以在 {@code yaml} 配置文件中指定 {@link RedisProperties} 中相关的属性
 * <p>注意: 该启动类必须在 {@link org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration} 自动配置类前加载
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see RedisProperties
 * @see RedisAutoConfiguration
 * @since 1.0.0.211009
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class)
@AutoConfigureAfter(BaseAutoConfiguration.class)
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
