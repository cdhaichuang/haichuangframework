package pro.haichuang.framework.sdk.wxmp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号配置文件
 *
 * <p>该类为 {@code hc-base} 核心的配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         wxmp:
 *           // AppId
 *           app-id:
 *           // AppSecret
 *           app-secret:
 *           // Token
 *           token:
 * </pre>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@ConfigurationProperties(prefix = "haichuang.sdk.wxmp")
public class WxMpProperties {

    /**
     * AppId
     */
    private String appId;

    /**
     * AppSecret
     */
    private String appSecret;

    /**
     * Token
     */
    private String token;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
