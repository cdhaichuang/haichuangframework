package pro.haichuang.framework.sdk.wxmp.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 微信公众号配置异常枚举
 *
 * <p>该类为微信公众号配置异常枚举
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public enum WxMpConfigErrorEnum implements BaseEnum {

    /**
     * 微信公众号配置异常
     */
    WX_MP_CONFIG_ERROR("SC10100", "微信公众号配置异常"),

    /**
     * AppId未配置
     */
    ACCESS_ID_NOT_CONFIGURED("SC10101", "AppId未配置"),

    /**
     * AppSecret未配置
     */
    ACCESS_SECRET_NOT_CONFIGURED("SC10102", "AppSecret未配置"),

    /**
     * Token未配置
     */
    Token_NOT_CONFIGURED("SC10103", "Token未配置");

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
    WxMpConfigErrorEnum(String value, String reasonPhrase) {
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
    public static WxMpConfigErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, WxMpConfigErrorEnum.class);
    }
}
