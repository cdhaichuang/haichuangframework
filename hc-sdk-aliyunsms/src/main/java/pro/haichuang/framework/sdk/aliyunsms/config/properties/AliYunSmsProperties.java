package pro.haichuang.framework.sdk.aliyunsms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云SMS配置文件
 *
 * <p>该类为 {@code hc-sdk-aliyunsms} SDK模块配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         aliyunsms:
 *           // AccessKeyId
 *           accessKeyId: xxx
 *           // AccessKeySecret
 *           accessKeySecret: xxx
 *           // 短信签名
 *           signName: xxx
 *           // 短信模板ID
 *           templateCode: xxx
 * </pre>
 * <hr>
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
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
     * 短信签名
     */
    private String signName;

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

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getDefaultTemplateCode() {
        return defaultTemplateCode;
    }

    public void setDefaultTemplateCode(String defaultTemplateCode) {
        this.defaultTemplateCode = defaultTemplateCode;
    }
}
