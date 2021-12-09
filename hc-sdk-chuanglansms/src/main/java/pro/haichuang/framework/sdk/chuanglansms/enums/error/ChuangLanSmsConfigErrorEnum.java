package pro.haichuang.framework.sdk.chuanglansms.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 创蓝短信发送配置枚举
 *
 * <p>该类为SMS发送配置枚举
 *
 * @author JiYinchuan
 * @since 1.2.0.211209
 */
public enum ChuangLanSmsConfigErrorEnum implements BaseEnum {

    /**
     * 创蓝短信配置异常
     */
    CONFIG_ERROR("SD10100", "创蓝短信配置异常"),

    /**
     * ApiAccount未配置
     */
    API_ACCOUNT_NOT_CONFIGURED("SD10101", "ApiAccount未配置"),

    /**
     * ApiPassword未配置
     */
    API_PASSWORD_NOT_CONFIGURED("SD10102", "ApiPassword未配置"),

    /**
     * 请求HOST域名未配置
     */
    HOST_NOT_CONFIGURED("SD10103", "请求HOST域名未配置"),

    /**
     * 短信签名未配置
     */
    SIGN_NAME_NOT_CONFIGURED("SD10104", "短信签名未配置"),

    /**
     * 发送消息内容未配置
     */
    SEND_MESSAGE_NOT_CONFIGURED("SD10105", "发送消息内容未配置");

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
     * @since 1.2.0.211209
     */
    ChuangLanSmsConfigErrorEnum(String value, String reasonPhrase) {
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
    public static ChuangLanSmsConfigErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, ChuangLanSmsConfigErrorEnum.class);
    }
}
