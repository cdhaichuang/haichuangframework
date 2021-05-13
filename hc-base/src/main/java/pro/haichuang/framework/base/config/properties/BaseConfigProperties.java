package pro.haichuang.framework.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置参数配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@ConfigurationProperties(prefix = "haichuang.config")
public class BaseConfigProperties {

    /**
     * 是否启用配置
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
