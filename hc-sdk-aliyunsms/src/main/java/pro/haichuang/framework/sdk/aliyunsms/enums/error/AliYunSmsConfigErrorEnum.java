package pro.haichuang.framework.sdk.aliyunsms.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 阿里云SMS配置异常枚举
 *
 * <p>该类为阿里云SMS配置异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public enum AliYunSmsConfigErrorEnum implements BaseEnum {

    /**
     * 阿里云SMS配置异常
     */
    CONFIG_ERROR("SA20100", "阿里云SMS配置异常"),

    /**
     * AccessKeyId未配置
     */
    ACCESS_KEY_ID_NOT_CONFIGURED("SA20101", "AccessKeyId未配置"),

    /**
     * AccessKeySecret未配置
     */
    ACCESS_KEY_SECRET_NOT_CONFIGURED("SA20102", "AccessKeySecret未配置"),

    /**
     * SignName未配置
     */
    SIGN_NAME_NOT_CONFIGURED("SA20103", "SignName未配置"),

    /**
     * TemplateCode未配置
     */
    TEMPLATE_CODE_NOT_CONFIGURED("SA20104", "TemplateCode未配置");

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
    AliYunSmsConfigErrorEnum(String value, String reasonPhrase) {
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
    public static AliYunSmsConfigErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, AliYunSmsConfigErrorEnum.class);
    }
}
