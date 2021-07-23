package pro.haichuang.framework.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 核心配置文件
 *
 * <p>该类为 {@code hc-base} 核心的配置文件类</p>
 * <hr/>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       // 是否启用配置
 *       enable: true
 * </pre>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
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
