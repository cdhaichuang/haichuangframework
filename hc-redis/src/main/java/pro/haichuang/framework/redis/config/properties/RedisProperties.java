package pro.haichuang.framework.redis.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis配置文件
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "haichuang.redis.config")
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
