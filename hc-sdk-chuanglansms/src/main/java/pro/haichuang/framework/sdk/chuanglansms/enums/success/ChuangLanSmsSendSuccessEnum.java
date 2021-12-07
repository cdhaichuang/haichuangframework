package pro.haichuang.framework.sdk.chuanglansms.enums.success;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 创蓝短信发送成功枚举
 *
 * <p>该类为SMS发送成功枚举
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public enum ChuangLanSmsSendSuccessEnum implements BaseEnum {

    /**
     * 华为云短信发送成功
     */
    SEND_SUCCESS("0", "创蓝短信发送成功");


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
    ChuangLanSmsSendSuccessEnum(String value, String reasonPhrase) {
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
    public static ChuangLanSmsSendSuccessEnum resolve(String value) {
        return BaseEnum.resolve(value, ChuangLanSmsSendSuccessEnum.class);
    }
}
