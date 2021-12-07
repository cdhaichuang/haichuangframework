package pro.haichuang.framework.sdk.wxmp.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 微信公众号执行异常枚举
 *
 * <p>该类为微信公众号执行异常枚举
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public enum WxMpExecuteErrorEnum implements BaseEnum {

    /**
     * 微信公众号执行异常
     */
    EXECUTE_ERROR("SC10300", "微信公众号执行异常"),

    /**
     * 获取数据异常
     */
    GET_ERROR("SC10310", "获取数据异常"),

    /**
     * 获取基础AccessToken异常
     */
    GET_BASE_ACCESS_TOKEN_ERROR("SC10311", "获取基础AccessToken异常"),

    /**
     * 获取网页AccessToken异常
     */
    GET_WEB_ACCESS_TOKEN_ERROR("SC10312", "获取网页AccessToken异常"),

    /**
     * 获取jsApi_ticket异常
     */
    GET_JS_API_TICKET_ERROR("SC10313", "获取jsApi_ticket异常"),

    /**
     * 获取用户信息异常
     */
    GET_USER_INFO_ERROR("SC10314", "获取用户信息异常"),

    /**
     * 刷新数据异常
     */
    REFRESH_ERROR("SC10320", "刷新数据异常"),

    /**
     * 刷新网页AccessToken异常
     */
    REFRESH_WEB_ACCESS_TOKEN_ERROR("SC10321", "刷新网页AccessToken异常");

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
    WxMpExecuteErrorEnum(String value, String reasonPhrase) {
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
    public static WxMpExecuteErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, WxMpExecuteErrorEnum.class);
    }
}
