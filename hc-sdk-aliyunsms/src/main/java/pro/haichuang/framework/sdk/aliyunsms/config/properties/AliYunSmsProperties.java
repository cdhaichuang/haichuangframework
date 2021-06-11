package pro.haichuang.framework.sdk.aliyunsms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云SMS配置文件
 *
 * @author JiYinchuan
 * @version 1.0
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
     * RegionId
     */
    private String regionId;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信模板ID, 发送国际/港澳台消息时, 请使用国际/港澳台短信模版
     */
    private String templateCode;

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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
