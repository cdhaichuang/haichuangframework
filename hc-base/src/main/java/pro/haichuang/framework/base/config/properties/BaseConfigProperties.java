package pro.haichuang.framework.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 核心配置文件
 *
 * @author JiYinchuan
 * @version 1.0
 */
@ConfigurationProperties(prefix = "haichuang")
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
