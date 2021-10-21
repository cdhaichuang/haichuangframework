package pro.haichuang.framework.sdk.huaweicloudsms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 华为云SMS配置文件
 *
 * <p>该类为 {@code hc-sdk-huaweicloudsms} SDK模块配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         huaweicloudsms:
 *           // AppKey
 *           app-key: xxx
 *           // AppSecret
 *           app-secret: xxx
 *           // AppUrl
 *           app-url: xxx
 *           // 默认短信签名, 可为空
 *           default-sign-name: xxx
 *           // 默认通道号, 可为空
 *           default-channel-number: xxx
 *           // 默认模版ID, 可为空
 *           default-template-id: xxx
 * </pre>
 * <hr>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@ConfigurationProperties(prefix = "haichuang.sdk.huaweicloudsms")
public class HuaWeiCloudSmsProperties {

    /**
     * AppKey
     */
    private String appKey;

    /**
     * AppSecret
     */
    private String appSecret;

    /**
     * AppUrl
     */
    private String appUrl;

    /**
     * 默认短信签名, 可为空
     */
    private String defaultSignName;

    /**
     * 默认通道号, 可为空
     */
    private String defaultChannelNumber;

    /**
     * 默认模版ID, 可为空
     */
    private String defaultTemplateId;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getDefaultSignName() {
        return defaultSignName;
    }

    public void setDefaultSignName(String defaultSignName) {
        this.defaultSignName = defaultSignName;
    }

    public String getDefaultChannelNumber() {
        return defaultChannelNumber;
    }

    public void setDefaultChannelNumber(String defaultChannelNumber) {
        this.defaultChannelNumber = defaultChannelNumber;
    }

    public String getDefaultTemplateId() {
        return defaultTemplateId;
    }

    public void setDefaultTemplateId(String defaultTemplateId) {
        this.defaultTemplateId = defaultTemplateId;
    }

    @Override
    public String toString() {
        return "HuaWeiCloudSmsProperties{" +
                "appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", appUrl='" + appUrl + '\'' +
                ", defaultSignName='" + defaultSignName + '\'' +
                ", defaultChannelNumber='" + defaultChannelNumber + '\'' +
                ", defaultTemplateId='" + defaultTemplateId + '\'' +
                '}';
    }
}
