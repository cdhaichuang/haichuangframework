package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户注册异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see pro.haichuang.framework.base.exception.client.RegisterException
 * @since 1.0.0.211014
 */
public enum RegisterErrorEnum implements BaseEnum {

    /**
     * 用户注册错误
     */
    REGISTER_ERROR("A0100", "用户注册错误"),

    /**
     * 用户未同意隐私协议
     */
    NOT_AGREE_PRIVACY_AGREEMENT("A0101", "用户未同意隐私协议"),

    /**
     * 注册国家或地区受限
     */
    RESTRICTED_COUNTRY_OR_REGION_OF_REGISTRATION("A0102", "注册国家或地区受限"),

    /**
     * 用户名校验失败
     */
    USERNAME_VERIFY_FAILED("A0110", "用户名校验失败"),

    /**
     * 用户名已存在
     */
    USERNAME_ALREADY_EXISTS("A0111", "用户名已存在"),

    /**
     * 用户名包含敏感词
     */
    USERNAME_CONTAINS_SENSITIVE_WORDS("A0112", "用户名包含敏感词"),

    /**
     * 用户名包含特殊字符
     */
    USERNAME_CONTAINS_SPECIAL_CHARACTERS("A0113", "用户名包含特殊字符"),

    /**
     * 密码校验失败
     */
    PASSWORD_VERIFY_FAILED("A0120", "密码校验失败"),

    /**
     * 密码长度不够
     */
    PASSWORD_LENGTH_NOT("A0121", "密码长度不够"),

    /**
     * 密码强度不够
     */
    PASSWORD_NOT_STRONG("A0122", "密码强度不够"),

    /**
     * 校验码输入错误
     */
    CODE_INPUT_ERROR("A0130", "校验码输入错误"),

    /**
     * 短信校验码输入错误
     */
    SMS_CODE_INPUT_ERROR("A0131", "短信校验码输入错误"),

    /**
     * 邮件校验码输入错误
     */
    EMAIL_CODE_INPUT_ERROR("A0132", "邮件校验码输入错误"),

    /**
     * 语音校验码输入错误
     */
    VOICE_CODE_INPUT_ERROR("A0133", "语音校验码输入错误"),

    /**
     * 用户证件异常
     */
    USER_CERTIFICATE_ERROR("A0140", "用户证件异常"),

    /**
     * 用户证件类型未选择
     */
    USER_CERTIFICATE_TYPE_NOT_SELECTED("A0141", "用户证件类型未选择"),

    /**
     * 大陆身份证编号校验非法
     */
    MAINLAND_ID_CARD_VERIFY_ILLEGAL("A0142", "大陆身份证编号校验非法"),

    /**
     * 护照编号校验非法
     */
    ILLEGAL_PASSPORT_VERIFY("A0143", "护照编号校验非法"),

    /**
     * 军官证编号校验非法
     */
    MILITARY_OFFICER_ID_VERIFY_ILLEGAL("A0144", "军官证编号校验非法"),

    /**
     * 用户基本信息校验失败
     */
    BASIC_INFO_VERIFY_FAILED("A0150", "用户基本信息校验失败"),

    /**
     * 手机格式校验失败
     */
    PHONE_FORMAT_VERIFY_FAILED("A0151", "手机格式校验失败"),

    /**
     * 地址格式校验失败
     */
    ADDRESS_FORMAT_VERIFY_FAILED("A0152", "地址格式校验失败"),

    /**
     * 邮箱格式校验失败
     */
    EMAIL_FORMAT_VERIFY_FAILED("A0153", "邮箱格式校验失败");

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
    RegisterErrorEnum(String value, String reasonPhrase) {
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
    public static RegisterErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, RegisterErrorEnum.class);
    }
}
