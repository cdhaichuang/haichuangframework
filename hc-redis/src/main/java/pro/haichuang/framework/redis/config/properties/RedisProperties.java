package pro.haichuang.framework.redis.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Redis配置文件
 *
 * <p>该类为 {@code hc-redis} 核心的配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       redis:
 *         // 是否启用配置
 *         enable: true
 * </pre>
 *
 * @author JiYinchuan
 * @see pro.haichuang.framework.redis.config.autoconfiguration.RedisAutoConfiguration
 * @since 1.1.0.211021
 */
@ConfigurationProperties(prefix = "haichuang.redis")
public class RedisProperties {

    /**
     * 是否启用Redis自动配置
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
