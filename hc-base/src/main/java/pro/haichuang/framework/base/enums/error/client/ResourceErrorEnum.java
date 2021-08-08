package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户资源异常枚举
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see pro.haichuang.framework.base.exception.client.ResourceException
 */
public enum ResourceErrorEnum implements BaseEnum {

    /**
     * 用户资源异常
     */
    USER_RESOURCE_ERROR("A0600", "用户资源异常"),

    /**
     * 账户余额不足
     */
    INSUFFICIENT_BALANCE("A0601", "账户余额不足"),

    /**
     * 用户磁盘空间不足
     */
    INSUFFICIENT_DISK_SPACE("A0602", "用户磁盘空间不足"),

    /**
     * 用户内存空间不足
     */
    INSUFFICIENT_MEMORY_SPACE("A0603", "用户内存空间不足"),

    /**
     * 用户 OSS 容量不足
     */
    INSUFFICIENT_OSS_CAPACITY("A0604", "用户 OSS 容量不足");

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
    ResourceErrorEnum(String value, String reasonPhrase) {
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
    public static ResourceErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, ResourceErrorEnum.class);
    }
}
