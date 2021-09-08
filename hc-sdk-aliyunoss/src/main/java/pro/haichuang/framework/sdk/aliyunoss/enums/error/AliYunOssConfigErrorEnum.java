package pro.haichuang.framework.sdk.aliyunoss.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 阿里云OSS配置异常枚举
 *
 * <p>该类为阿里云OSS配置异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum AliYunOssConfigErrorEnum implements BaseEnum {

    /**
     * 阿里云OSS配置异常
     */
    ALI_YUN_OSS_CONFIG_ERROR("SA10100", "阿里云OSS配置异常"),

    /**
     * AccessKeyId未配置
     */
    ACCESS_KEY_ID_NOT_CONFIGURED("SA10101", "AccessKeyId未配置"),

    /**
     * AccessKeySecret未配置
     */
    ACCESS_KEY_SECRET_NOT_CONFIGURED("SA10102", "AccessKeySecret未配置"),

    /**
     * BucketName未配置
     */
    BUCKET_NAME_NOT_CONFIGURED("SA10103", "BucketName未配置"),

    /**
     * EndPoint未配置
     */
    END_POINT_NOT_CONFIGURED("SA10104", "EndPoint未配置");

    /**
     * 枚举值
     */
    private final String value;

    /**
     * 枚举信息
     */
    private final String reasonPhrase;

    /**
     * 构造器
     *
     * @param value        枚举值
     * @param reasonPhrase 枚举信息
     * @since 1.0.0
     */
    AliYunOssConfigErrorEnum(String value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static AliYunOssConfigErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, AliYunOssConfigErrorEnum.class);
    }
}
