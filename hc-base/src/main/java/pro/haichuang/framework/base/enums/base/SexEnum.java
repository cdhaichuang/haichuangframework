package pro.haichuang.framework.base.enums.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 性别枚举
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
public enum SexEnum implements BaseEnum {

    /**
     * 未知
     */
    UNKNOWN("0", "UNKNOWN"),

    /**
     * 男
     */
    MAN("1", "男"),

    /**
     * 女
     */
    WOMAN("2", "女");

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
    SexEnum(String value, String reasonPhrase) {
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
    public static SexEnum resolve(String value) {
        return BaseEnum.resolve(value, SexEnum.class);
    }
}
