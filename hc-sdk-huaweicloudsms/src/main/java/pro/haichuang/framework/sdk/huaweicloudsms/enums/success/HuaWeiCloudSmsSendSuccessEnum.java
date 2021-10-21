package pro.haichuang.framework.sdk.huaweicloudsms.enums.success;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 华为云SMS发送成功枚举
 *
 * <p>该类为SMS发送成功枚举
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public enum HuaWeiCloudSmsSendSuccessEnum implements BaseEnum {

    /**
     * 华为云SMS发送成功
     */
    HUA_WEI_CLOUD_SMS_SEND_SUCCESS("000000", "华为云SMS发送成功");


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
    HuaWeiCloudSmsSendSuccessEnum(String value, String reasonPhrase) {
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
    public static HuaWeiCloudSmsSendSuccessEnum resolve(String value) {
        return BaseEnum.resolve(value, HuaWeiCloudSmsSendSuccessEnum.class);
    }
}
