package pro.haichuang.framework.sdk.huaweicloudobs.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.exception.client.RequestParamException;

/**
 * 华为云OBS配置文件
 *
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         huaweicloudobs:
 *           # AccessKeyId
 *           access-key-id: xxx
 *           # AccessKeySecret
 *           access-key-secret: xxx
 *           # BucketName
 *           bucket-name: xxx
 *           # Endpoint地域节点
 *           endpoint: xxx
 * </pre>
 * <hr>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@ConfigurationProperties(prefix = "haichuang.sdk.huaweicloudobs")
public class HuaWeiCloudObsProperties {

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
     * Endpoint地域节点
     */
    private String endpoint;

    /**
     * 获取BucketDomain访问域名
     *
     * @return BucketDomain访问域名
     * @since 1.1.0.211021
     */
    public String getBucketDomain() {
        if (endpoint == null || endpoint.isEmpty()) {
            throw new RequestParamException(RequestParamErrorEnum.PARAMETER_EMPTY, "[Endpoint地域节点] 未在Yaml进行配置");
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
