package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 请求服务异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.base.exception.client.RequestServerException
 * @since 1.0.0
 */
public enum RequestServerErrorEnum implements BaseEnum {

    /**
     * 用户请求服务异常
     */
    SERVICE_ERROR("A0500", "用户请求服务异常"),

    /**
     * 请求次数超出限制
     */
    NUMBER_OF_REQUESTS_EXCEEDS_LIMIT("A0501", "请求次数超出限制"),

    /**
     * 请求并发数超出限制
     */
    CONCURRENCY_EXCEEDS_LIMIT("A0502", "请求并发数超出限制"),

    /**
     * 用户操作请等待
     */
    OPERATION_PLEASE_WAIT("A0503", "用户操作请等待"),

    /**
     * WebSocket 连接异常
     */
    WEBSOCKET_CONNECTION_ERROR("A0504", "WebSocket 连接异常"),

    /**
     * WebSocket 连接断开
     */
    WEBSOCKET_DISCONNECT("A0505", "WebSocket 连接断开"),

    /**
     * 用户重复请求
     */
    REPEAT_REQUEST("A0506", "用户重复请求");

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
     */
    RequestServerErrorEnum(String value, String reasonPhrase) {
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
    public static RequestServerErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, RequestServerErrorEnum.class);
    }
}
