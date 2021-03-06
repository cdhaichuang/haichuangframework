package pro.haichuang.framework.mybatis.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Mybatis参数配置
 *
 * <p>该类为 {@code hc-mybatis} 核心的配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       mybatis:
 *         // 是否启用配置
 *         enable: true
 * </pre>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@Component
@ConfigurationProperties(prefix = "haichuang.mybatis")
public class MybatisProperties {

    /**
     * 是否启用自动配置
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
