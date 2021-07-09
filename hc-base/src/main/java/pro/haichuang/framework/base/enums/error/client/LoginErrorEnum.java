package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户登录异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum LoginErrorEnum implements BaseEnum {

    /**
     * 用户登录异常
     */
    USER_LOGIN_ABNORMAL("A0200", "用户登录异常"),

    /**
     * 用户账户不存在
     */
    USER_ACCOUNT_NOT_EXIST("A0201", "用户账户不存在"),

    /**
     * 用户账户被冻结
     */
    USER_ACCOUNT_FROZEN("A0202", "用户账户被冻结"),

    /**
     * 用户账户已作废
     */
    USER_ACCOUNT_INVALID("A0203", "用户账户已作废"),

    /**
     * 用户账户已过期
     */
    USER_ACCOUNT_EXPIRE("A0204", "用户账户已过期"),

    /**
     * 用户凭证已过期
     */
    USER_CREDENTIALS_EXPIRE("A0205", "用户凭证已过期"),

    /**
     * 用户密码错误
     */
    USER_PASSWORD_VERIFY_FAILED("A0210", "用户密码错误"),

    /**
     * 用户输入密码错误次数超限
     */
    USER_INPUT_ERROR_PASSWORD_OVERRUN("A0211", "用户输入密码错误次数超限"),

    /**
     * 用户身份校验失败
     */
    USER_IDENTITY_VERIFICATION_FAILED("A0220", "用户身份校验失败"),

    /**
     * 用户指纹识别失败
     */
    USER_FINGERPRINT_VERIFICATION_FAILED("A0221", "用户指纹识别失败"),

    /**
     * 用户面容识别失败
     */
    USER_FACE_VERIFICATION_FAILED("A0222", "用户面容识别失败"),

    /**
     * 用户未获得第三方登录授权
     */
    USER_NOT_AUTHORIZED_BY_THIRTY_PARTY("A0223", "用户未获得第三方登录授权"),

    /**
     * 用户登录已过期
     */
    USER_LOGIN_EXPIRED("A0230", "用户登录已过期"),

    /**
     * 用户验证码错误
     */
    USER_CODE_VERIFICATION_FAILED("A0240", "用户验证码错误"),

    /**
     * 用户验证码尝试次数超限
     */
    USER_INPUT_ERROR_CODE_OVERRUN("A0241", "用户验证码尝试次数超限");

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
    LoginErrorEnum(String value, String reasonPhrase) {
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
    public static LoginErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, LoginErrorEnum.class);
    }
}
