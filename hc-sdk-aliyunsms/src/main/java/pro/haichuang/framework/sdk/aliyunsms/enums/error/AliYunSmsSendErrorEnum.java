package pro.haichuang.framework.sdk.aliyunsms.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 阿里云短信发送异常枚举
 *
 * <p>该类为阿里云短信发送异常枚举
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public enum AliYunSmsSendErrorEnum implements BaseEnum {

    /**
     * 阿里云短信发送异常
     */
    SEND_ERROR("SA20200", "阿里云短信发送异常"),

    /**
     * 手机号格式错误
     */
    MALFORMED_PHONE_NUMBER("SA20201", "手机号格式错误");

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
    AliYunSmsSendErrorEnum(String value, String reasonPhrase) {
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
    public static AliYunSmsSendErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, AliYunSmsSendErrorEnum.class);
    }
}
