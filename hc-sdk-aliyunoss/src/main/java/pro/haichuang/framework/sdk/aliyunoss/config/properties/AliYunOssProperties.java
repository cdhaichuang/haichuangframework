package pro.haichuang.framework.sdk.aliyunoss.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云OSS配置文件
 *
 * @author JiYinchuan
 * @version 1.0
 */
@ConfigurationProperties(prefix = "haichuang.sdk.oss")
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
    private String endPoint;

}
