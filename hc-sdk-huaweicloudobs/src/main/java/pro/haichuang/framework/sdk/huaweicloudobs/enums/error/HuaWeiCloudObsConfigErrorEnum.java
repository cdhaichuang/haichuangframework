package pro.haichuang.framework.sdk.huaweicloudobs.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 华为云OBS配置异常枚举
 *
 * <p>该类为华为云OBS配置异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public enum HuaWeiCloudObsConfigErrorEnum implements BaseEnum {

    /**
     * 华为云OBS配置异常
     */
    HUA_WEI_CLOUD_OBS_CONFIG_ERROR("SB10100", "华为云OBS配置异常"),

    /**
     * AccessKeyId未配置
     */
    ACCESS_KEY_ID_NOT_CONFIGURED("SB10101", "AccessKeyId未配置"),

    /**
     * AccessKeySecret未配置
     */
    ACCESS_KEY_SECRET_NOT_CONFIGURED("SB10102", "AccessKeySecret未配置"),

    /**
     * BucketName未配置
     */
    BUCKET_NAME_NOT_CONFIGURED("SB10103", "BucketName未配置"),

    /**
     * EndPoint未配置
     */
    END_POINT_NOT_CONFIGURED("SB10104", "EndPoint未配置");

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
     * @since 1.0.0.211009
     */
    HuaWeiCloudObsConfigErrorEnum(String value, String reasonPhrase) {
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
    public static HuaWeiCloudObsConfigErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, HuaWeiCloudObsConfigErrorEnum.class);
    }
}
