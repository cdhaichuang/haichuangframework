package pro.haichuang.framework.sdk.huaweicloudsms.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 华为云短信发送配置枚举
 *
 * <p>该类为SMS发送配置枚举
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public enum HuaWeiCloudSmsConfigErrorEnum implements BaseEnum {

    /**
     * 华为云短信配置异常
     */
    CONFIG_ERROR("SB20100", "华为云短信配置异常"),

    /**
     * AccessKeyId未配置
     */
    APP_KEY_NOT_CONFIGURED("SB20101", "AppKey未配置"),

    /**
     * AccessKeySecret未配置
     */
    APP_SECRET_NOT_CONFIGURED("SB20102", "AppSecret未配置"),

    /**
     * AppUrl未配置
     */
    APP_URL_NOT_CONFIGURED("SB20103", "AppUrl未配置"),

    /**
     * 短信签名未配置
     */
    SIGN_NAME_NOT_CONFIGURED("SB20104", "短信签名未配置"),

    /**
     * 通道号未配置
     */
    CHANNEL_NUMBER_NOT_CONFIGURED("SB20105", "通道号未配置"),

    /**
     * 模版ID未配置
     */
    TEMPLATE_ID_NOT_CONFIGURED("SB20106", "模版ID未配置");


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
     * @since 1.1.0.211021
     */
    HuaWeiCloudSmsConfigErrorEnum(String value, String reasonPhrase) {
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
    public static HuaWeiCloudSmsConfigErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, HuaWeiCloudSmsConfigErrorEnum.class);
    }
}
