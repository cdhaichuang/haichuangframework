package pro.haichuang.framework.sdk.aliyunoss.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.exception.client.RequestParamException;

/**
 * 阿里云OSS配置文件
 *
 * <p>该类为 {@code hc-sdk-aliyunoss} SDK模块配置文件类
 * <hr>
 * Example:
 * <pre>
 *     # ========================= Haichuang Setting =========================
 *     haichuang:
 *       sdk:
 *         aliyunoss:
 *           // AccessKeyId
 *           accessKeyId: xxx
 *           // AccessKeySecret
 *           accessKeySecret: xxx
 *           // BucketName
 *           bucketName: xxx
 *           // Endpoint地域节点
 *           endpoint: xxx
 * </pre>
 * <hr>
 * <p>参数配置完毕后可通过 {@link #getBucketDomain()} 方法获取访问 {@code Bucket域名} 地址
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
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
     * Endpoint地域节点
     */
    private String endpoint;

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
