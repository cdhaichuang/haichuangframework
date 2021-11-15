package pro.haichuang.framework.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 核心配置文件
 *
 * <p>该类为 {@code hc-base} 核心的配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       // 是否启用配置
 *       enable: true
 *       // 项目代号
 *       projectCode:
 *       // 请求超时时间, 可自定义时间单位, 默认时间单位为 [秒], 默认值为 [3s]
 *       requestTimeoutTime: 3s
 * </pre>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@ConfigurationProperties(prefix = "haichuang")
public class BaseConfigProperties {

    /**
     * 是否启用配置
     */
    private Boolean enable;

    /**
     * 项目代号
     */
    private String projectCode;

    /**
     * 请求超时时间, 可自定义时间单位, 默认时间单位为 [秒], 默认值为 [3s]
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration requestTimeoutTime = Duration.ofSeconds(3);

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Duration getRequestTimeoutTime() {
        return requestTimeoutTime;
    }

    public void setRequestTimeoutTime(Duration requestTimeoutTime) {
        this.requestTimeoutTime = requestTimeoutTime;
    }
}
