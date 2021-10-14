package pro.haichuang.framework.sdk.wxmp.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 微信公众号延时队列异常枚举
 *
 * <p>该类为微信公众号延时队列异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public enum WxMpDelayQueueErrorEnum implements BaseEnum {

    /**
     * 微信公众号延时队列异常
     */
    WX_MP_DELAY_QUEUE_ERROR("SC10200", "微信公众号延时队列异常"),

    /**
     * 异常终止
     */
    TERMINATION_ERROR("SC10201", "异常终止"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("SC10202", "未知异常");

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
    WxMpDelayQueueErrorEnum(String value, String reasonPhrase) {
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
    public static WxMpDelayQueueErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, WxMpDelayQueueErrorEnum.class);
    }
}
