package pro.haichuang.framework.sdk.aliyunsms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信配置文件
 *
 * <p>该类为 {@code hc-sdk-aliyunsms} SDK模块配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         aliyunsms:
 *           # AccessKeyId
 *           access-key-id: xxx
 *           # AccessKeySecret
 *           access-key-secret: xxx
 *           # 短信签名
 *           default-sign-name: xxx
 *           # 默认短信模板ID, 可为空
 *           default-template-code: xxx
 * </pre>
 * <hr>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@ConfigurationProperties(prefix = "haichuang.sdk.aliyunsms")
public class AliYunSmsProperties {

    /**
     * AccessKeyId
     */
    private String accessKeyId;

    /**
     * AccessKeySecret
     */
    private String accessKeySecret;

    /**
     * 默认短信签名
     */
    private String defaultSignName;

    /**
     * 默认短信模板ID, 发送国际/港澳台消息时, 请使用国际/港澳台短信模版
     */
    private String defaultTemplateCode;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getDefaultSignName() {
        return defaultSignName;
    }

    public void setDefaultSignName(String defaultSignName) {
        this.defaultSignName = defaultSignName;
    }

    public String getDefaultTemplateCode() {
        return defaultTemplateCode;
    }

    public void setDefaultTemplateCode(String defaultTemplateCode) {
        this.defaultTemplateCode = defaultTemplateCode;
    }
}
