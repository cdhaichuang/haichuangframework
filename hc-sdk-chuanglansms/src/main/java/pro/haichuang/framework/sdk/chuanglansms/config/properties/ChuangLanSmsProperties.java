package pro.haichuang.framework.sdk.chuanglansms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 创蓝短信配置文件
 *
 * <p>该类为 {@code hc-sdk-chuanglansms} SDK模块配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         chuanglansms:
 *           # ApiAccount
 *           api-account: xxx
 *           # ApiPassword
 *           api-password: xxx
 *           # 请求Host, 此处只填写域名
 *           host: xxx
 *           # 默认短信签名, 可为空
 *           default-sign-name: xxx
 * </pre>
 * <hr>
 *
 * @author JiYinchuan
 * @since 1.2.0.211209
 */
@ConfigurationProperties(prefix = "haichuang.sdk.chuanglansms")
public class ChuangLanSmsProperties {

    /**
     * ApiAccount
     */
    private String apiAccount;

    /**
     * ApiPassword
     */
    private String apiPassword;

    /**
     * 请求Host, 此处只填写域名
     */
    private String host;

    /**
     * 默认短信签名
     */
    private String defaultSignName;

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDefaultSignName() {
        return defaultSignName;
    }

    public void setDefaultSignName(String defaultSignName) {
        this.defaultSignName = defaultSignName;
    }
}
