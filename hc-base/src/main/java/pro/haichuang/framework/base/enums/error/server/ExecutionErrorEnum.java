package pro.haichuang.framework.base.enums.error.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 系统执行超时异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ExecutionErrorEnum implements BaseEnum {

    /**
     * 系统执行超时
     */
    EXECUTION_TIMEOUT("B0100", "系统执行超时"),

    /**
     * 系统订单处理超时
     */
    EXECUTION_ORDER_TIMEOUT("B0101", "系统订单处理超时");

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
    ExecutionErrorEnum(String value, String reasonPhrase) {
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
    public static ExecutionErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, ExecutionErrorEnum.class);
    }
}
