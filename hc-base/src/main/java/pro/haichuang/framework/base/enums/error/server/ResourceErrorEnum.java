package pro.haichuang.framework.base.enums.error.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 系统资源异常枚举
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see pro.haichuang.framework.base.exception.server.ResourceException
 */
public enum ResourceErrorEnum implements BaseEnum {

    /**
     * 系统资源异常
     */
    RESOURCE_ERROR("B0300", "系统资源异常"),

    /**
     * 系统资源耗尽
     */
    RESOURCE_EXHAUSTION("B0310", "系统资源耗尽"),

    /**
     * 系统磁盘空间耗尽
     */
    INSUFFICIENT_DISK_SPACE("B0311", "系统磁盘空间耗尽"),

    /**
     * 系统内存耗尽
     */
    INSUFFICIENT_MEMORY_SPACE("B0312", "系统内存耗尽"),

    /**
     * 文件句柄耗尽
     */
    INSUFFICIENT_FILE_HANDLE("B0313", "文件句柄耗尽"),

    /**
     * 系统连接池耗尽
     */
    INSUFFICIENT_CONNECTION_POOL("B0314", "系统连接池耗尽"),

    /**
     * 系统线程池耗尽
     */
    INSUFFICIENT_THREAD_POOL("B0315", "系统线程池耗尽"),

    /**
     * 系统资源访问异常
     */
    RESOURCE_ACCESS_ERROR("B0320", "系统资源访问异常"),

    /**
     * 系统读取磁盘文件失败
     */
    READ_THE_DISK_FILE_ERROR("B0321", "系统读取磁盘文件失败");

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
