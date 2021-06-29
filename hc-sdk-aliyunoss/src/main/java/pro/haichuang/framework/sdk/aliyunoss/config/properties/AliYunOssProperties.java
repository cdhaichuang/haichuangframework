package pro.haichuang.framework.sdk.aliyunoss.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.exception.client.RequestParamException;

/**
 * 阿里云OSS配置文件
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@ConfigurationProperties(prefix = "haichuang.sdk.aliyunoss")
public class AliYunOssProperties {

    /**
     * AccessKeyId
     */
    private String accessKeyId;

    /**
     * AccessKeySecret
     */
    private String accessKeySecret;

    /**
     * BucketName
     */
    private String bucketName;

    /**
     * Endpoint
     */
    private String endpoint;

    public String getBucketDomain() {
        if (endpoint == null || endpoint.isEmpty()) {
            throw new RequestParamException(RequestParamErrorEnum.PARAMETER_EMPTY, "[Endpoint] 未在Yaml进行配置");
        }
        if (bucketName == null || bucketName.isEmpty()) {
            throw new RequestParamException(RequestParamErrorEnum.PARAMETER_EMPTY, "[BucketName] 未在Yaml进行配置");
        }
        return "https://".concat(bucketName).concat(".")
                .concat(endpoint.replaceAll("^(http|https)", "")
                        .replaceAll("^/.*", ""));
    }

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

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
