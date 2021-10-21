package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 访问权限异常枚举
 *
 * @author JiYinchuan
 * @see pro.haichuang.framework.base.exception.client.AuthorityException
 * @since 1.1.0.211021
 */
public enum AuthorityErrorEnum implements BaseEnum {

    /**
     * 访问权限异常
     */
    AUTHORITY_ERROR("A0300", "访问权限异常"),

    /**
     * 访问未授权
     */
    UNAUTHORIZED("A0301", "访问未授权"),

    /**
     * 正在授权中
     */
    AUTHORIZING("A0302", "正在授权中"),

    /**
     * 用户授权申请被拒绝
     */
    AUTHORITY_REJECTED("A0303", "用户授权申请被拒绝"),

    /**
     * 因访问对象隐私设置被拦截
     */
    BLOCKED_DUE_TO_PRIVACY_SETTINGS("A0310", "因访问对象隐私设置被拦截"),

    /**
     * 授权已过期
     */
    AUTHORITY_EXPIRED("A0311", "授权已过期"),

    /**
     * 无权限使用 API
     */
    NO_PERMISSION_TO_USE_API("A0312", "无权限使用 API"),

    /**
     * 用户访问被拦截
     */
    ACCESS_BLOCKED("A0320", "用户访问被拦截"),

    /**
     * 黑名单用户
     */
    BLACKLISTED_USER("A0321", "黑名单用户"),

    /**
     * 账号被冻结
     */
    ACCOUNT_IS_FROZEN("A0322", "账号被冻结"),

    /**
     * 非法 IP 地址
     */
    ILLEGAL_IP_ADDRESS("A0323", "非法 IP 地址"),

    /**
     * 网关访问受限
     */
    GATEWAY_ACCESS_RESTRICTED("A0324", "网关访问受限"),

    /**
     * 地域黑名单
     */
    BLACKLISTED_REGIONAL("A0325", "地域黑名单"),

    /**
     * 服务已欠费
     */
    SERVICE_ARREARS("A0330", "服务已欠费"),

    /**
     * 用户签名异常
     */
    USER_SIGNATURE_ERROR("A0340", "用户签名异常"),

    /**
     * RSA 签名错误
     */
    RSA_SIGNATURE_ERROR("A0341", "RSA 签名错误");

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
    AuthorityErrorEnum(String value, String reasonPhrase) {
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
    public static AuthorityErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, AuthorityErrorEnum.class);
    }
}
