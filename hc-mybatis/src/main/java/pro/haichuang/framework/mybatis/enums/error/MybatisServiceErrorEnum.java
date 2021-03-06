package pro.haichuang.framework.mybatis.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * MybatisService异常枚举
 *
 * <p>该类为 [MybatisService] 层异常枚举
 *
 * @author JiYinchuan
 * @see pro.haichuang.framework.mybatis.exception.MybatisServiceApplication
 * @since 1.1.0.211021
 */
public enum MybatisServiceErrorEnum implements BaseEnum {

    /**
     * MybatisService异常
     */
    MYBATIS_SERVICE_ERROR("M0100", "MybatisService异常"),

    /**
     * ID为空
     */
    ID_IS_NULL("M0101", "ID为空"),

    /**
     * 结果集为空
     */
    RESULT_SET_IS_NULL("M0102", "结果集为空");

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
    MybatisServiceErrorEnum(String value, String reasonPhrase) {
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
    public static MybatisServiceErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, MybatisServiceErrorEnum.class);
    }
}
