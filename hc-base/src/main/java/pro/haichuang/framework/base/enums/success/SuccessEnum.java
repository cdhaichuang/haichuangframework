package pro.haichuang.framework.base.enums.success;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 成功枚举
 *
 * @author JiYinchuan
 * @see pro.haichuang.framework.base.response.ResultVO
 * @since 1.1.0.211021
 */
public enum SuccessEnum implements BaseEnum {

    /**
     * 一切 ok
     */
    OK("00000", "OK");

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
    SuccessEnum(String value, String reasonPhrase) {
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
    public static SuccessEnum resolve(String value) {
        return BaseEnum.resolve(value, SuccessEnum.class);
    }
}
