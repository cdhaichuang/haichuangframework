package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户当前版本异常枚举
 *
 * @author JiYinchuan
 * @version 1.0
 */
public enum VersionErrorEnum implements BaseEnum {

    /**
     * 用户当前版本异常
     */
    VERSION_ERROR("A0800", "用户当前版本异常"),

    /**
     * 用户安装版本与系统不匹配
     */
    VERSION_MISMATCH("A0801", "用户安装版本与系统不匹配"),

    /**
     * 用户安装版本过低
     */
    VERSION_IS_TOO_LOW("A0802", "用户安装版本过低"),

    /**
     * 用户安装版本过高
     */
    VERSION_IS_TOO_HIGH("A0803", "用户安装版本过高"),

    /**
     * 用户安装版本已过期
     */
    VERSION_IS_EXPIRED("A0804", "用户安装版本已过期"),

    /**
     * 用户 API 请求版本不匹配
     */
    API_VERSION_MISMATCH("A0805", "用户 API 请求版本不匹配"),

    /**
     * 用户 API 请求版本过高
     */
    API_VERSION_IS_TOO_HIGH("A0806", "用户 API 请求版本过高"),

    /**
     * 用户 API 请求版本过低
     */
    API_VERSION_IS_TOO__LOW("A0807", "用户 API 请求版本过低");

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
    VersionErrorEnum(String value, String reasonPhrase) {
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
    public static VersionErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, VersionErrorEnum.class);
    }
}
