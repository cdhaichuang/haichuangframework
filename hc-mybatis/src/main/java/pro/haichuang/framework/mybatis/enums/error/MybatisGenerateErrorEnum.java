package pro.haichuang.framework.mybatis.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * Mybatis代码生成异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public enum MybatisGenerateErrorEnum implements BaseEnum {

    /**
     * Mybatis代码生成异常
     */
    MYBATIS_CODE_GENERATE_ERROR("H0200", "Mybatis代码生成异常"),

    /**
     * 基本配置异常
     */
    BASIC_ERROR("H0210", "数据源异常"),

    /**
     * 数据源配置异常
     */
    DATA_SOURCE_ERROR("H0220", "数据源异常"),

    /**
     * 数据源URL为空
     */
    DATA_SOURCE_URL_IS_BLANK("H0221", "数据源URL为空"),

    /**
     * 数据源用户名为空
     */
    DATA_SOURCE_USERNAME_IS_BLANK("H0222", "数据源用户名为空"),

    /**
     * 数据源密码为空
     */
    DATA_SOURCE_PASSWORD_IS_BLANK("H0223", "数据源密码为空"),

    /**
     * 包配置异常
     */
    PACKAGE_ERROR("H0230", "包配置异常"),

    /**
     * 父包模块名为空
     */
    PACKAGE_PARENT_MODEL_NAME_IS_BLANK("H0231", "包配置异常");


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
