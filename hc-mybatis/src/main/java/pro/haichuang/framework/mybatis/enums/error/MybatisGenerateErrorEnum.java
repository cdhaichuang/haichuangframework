package pro.haichuang.framework.mybatis.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * MybatisPlus代码生成异常枚举
 *
 * <p>该类为 [MybatisPlus] 代码生成异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.mybatis.exception.MybatisGenerateErrorApplication
 * @since 1.0.0
 */
public enum MybatisGenerateErrorEnum implements BaseEnum {

    /**
     * Mybatis代码生成异常
     */
    MYBATIS_CODE_GENERATE_ERROR("M0200", "Mybatis代码生成异常"),

    /**
     * 基本配置异常
     */
    BASIC_ERROR("M0201", "基本配置异常"),

    /**
     * 数据源配置异常
     */
    DATA_SOURCE_ERROR("M0202", "数据源配置异常"),

    /**
     * 数据源URL为空
     */
    DATA_SOURCE_URL_IS_BLANK("M0203", "数据源URL为空"),

    /**
     * 数据源用户名为空
     */
    DATA_SOURCE_USERNAME_IS_BLANK("M0204", "数据源用户名为空"),

    /**
     * 数据源密码为空
     */
    DATA_SOURCE_PASSWORD_IS_BLANK("M0205", "数据源密码为空"),

    /**
     * 包配置异常
     */
    PACKAGE_ERROR("M0206", "包配置异常"),

    /**
     * 父包模块名为空
     */
    PACKAGE_PARENT_MODEL_NAME_IS_BLANK("M0207", "包配置异常");


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
    MybatisGenerateErrorEnum(String value, String reasonPhrase) {
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
    public static MybatisGenerateErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, MybatisGenerateErrorEnum.class);
    }
}
