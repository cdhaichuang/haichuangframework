package pro.haichuang.framework.base.enums.error.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 系统容灾功能被触发异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see pro.haichuang.framework.base.exception.server.DisasterRecoveryException
 * @since 1.0.0.211014
 */
public enum DisasterRecoveryErrorEnum implements BaseEnum {

    /**
     * 系统容灾功能被触发
     */
    TRIGGER_DISASTER_RECOVERY("B0200", "系统容灾功能被触发"),

    /**
     * 系统限流
     */
    TRIGGER_LIMITING("B0210", "系统限流"),

    /**
     * 系统功能降级
     */
    TRIGGER_DOWNGRADE("B0220", "系统功能降级");

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
     * @since 1.0.0.211014
     */
    DisasterRecoveryErrorEnum(String value, String reasonPhrase) {
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
    public static DisasterRecoveryErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, DisasterRecoveryErrorEnum.class);
    }
}
