package pro.haichuang.framework.sdk.aliyunsms.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 阿里云SMS发送异常枚举
 *
 * <p>该类为阿里云SMS发送异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("SpellCheckingInspection")
public enum AliYunSmsSendErrorEnum implements BaseEnum {

    /**
     * 阿里云SMS发送异常
     */
    ALI_YUN_SMS_SEND_ERROR("SA20200", "isv.BLACK_KEY_CONTROL_LIMIT", "阿里云SMS发送异常"),

    /**
     * 黑名单管控
     * desc. 建议联系平台解除黑名单
     */
    ISV_BLACK_KEY_CONTROL_LIMIT("SA20201", "isv.BLACK_KEY_CONTROL_LIMIT", "黑名单管控"),

    /**
     * 非法手机号
     * desc. 建议使用正确的手机号
     */
    ISV_MOBILE_NUMBER_ILLEGAL("SA20202", "isv.MOBILE_NUMBER_ILLEGAL", "非法手机号"),

    /**
     * 重复过滤
     * desc. 建议减少每分钟发送数量
     */
    VALUE_M_MC("SA20203", "VALVE:M_MC", "重复过滤"),

    /**
     * 重复过滤
     * desc. 建议减少每小时发送数量
     */
    VALUE_H_MC("SA20204", "VALVE:H_MC", "重复过滤"),

    /**
     * 重复过滤
     * desc. 建议减少每天发送数量
     */
    VALUE_D_MC("SA20205", "VALVE:D_MC", "重复过滤"),

    /**
     * 账户异常
     * desc. 建议联系平台确认账号
     */
    ISV_ACCOUNT_ABNORMAL("SA20206", "isv.ACCOUNT_ABNORMAL", "账户异常"),

    /**
     * 账户余额不足
     * desc. 建议进行账户充值
     */
    ISV_AMOUNT_NOT_ENOUGH("SA20207", "isv.AMOUNT_NOT_ENOUGH", "账户余额不足"),

    /**
     * 账户不存在
     * desc. 建议开通账户
     */
    ISV_ACCOUNT_NOT_EXISTS("SA20208", "isv.ACCOUNT_NOT_EXISTS", "账户不存在"),

    /**
     * 系统错误
     * desc. 建议联系平台核查原因
     */
    ISP_SYSTEM_ERROR("SA20209", "isp.SYSTEM_ERROR", "系统错误"),

    /**
     * 短信签名不合法
     * desc. 建议重新申请签名
     */
    ISV_SMS_SIGNATURE_ILLEGAL("SA20210", "isv.SMS_SIGNATURE_ILLEGAL", "短信签名不合法"),

    /**
     * 短信模板不合法
     * desc. 建议重新申请模版
     */
    ISV_SMS_TEMPLATE_ILLEGAL("SA20211", "isv.SMS_TEMPLATE_ILLEGAL", "短信模板不合法"),

    /**
     * 模板缺少变量
     * desc. 建议修改模版
     */
    ISV_TEMPLATE_MISSING_PARAMETERS("SA20212", "isv.TEMPLATE_MISSING_PARAMETERS", "模板缺少变量"),

    /**
     * 模板变量里包含非法关键字
     * desc. 建议修改模版
     */
    ISV_TEMPLATE_PARAMS_ILLEGAL("SA20213", "isv.TEMPLATE_PARAMS_ILLEGAL", "模板变量里包含非法关键字"),

    /**
     * 未开通云通信产品的阿里云客户
     * desc. 建议开通云通信产品
     */
    ISV_PRODUCT_UN_SUBSCRIPT("SA20214", "isv.PRODUCT_UN_SUBSCRIPT", "未开通云通信产品的阿里云客户"),

    /**
     * 手机号码数量超过限制
     * desc. 建议减少手机号码
     */
    ISV_MOBILE_COUNT_OVER_LIMIT("SA20215", "isv.MOBILE_COUNT_OVER_LIMIT", "手机号码数量超过限制"),

    /**
     * 参数超出长度限制
     * desc. 建议修改参数长度
     */
    ISV_PARAM_LENGTH_LIMIT("SA20216", "isv.PARAM_LENGTH_LIMIT", "参数超出长度限制"),

    /**
     * 参数异常
     * desc. 建议使用正确的参数
     */
    ISV_INVALID_PARAMETERS("SA20217", "isv.INVALID_PARAMETERS", "参数异常"),

    /**
     * 关键字拦截
     * desc. 建议修改短信内容
     */
    FILTER("SA20218", "FILTER", "关键字拦截"),

    /**
     * 产品未开通
     * desc. 建议订购产品
     */
    ISV_PRODUCT_UNSUBSCRIBE("SA20219", "isv.PRODUCT_UNSUBSCRIBE", "产品未开通"),

    /**
     * 业务限流
     * desc. 建议联系平台核查原因
     */
    ISV_BUSINESS_LIMIT_CONTROL("SA20220", "isv.BUSINESS_LIMIT_CONTROL", "业务限流"),

    /**
     * 业务停机
     * desc. 建议联系平台核查原因
     */
    ISV_OUT_OF_SERVICE("SA20221", "isv.OUT_OF_SERVICE", "业务停机"),

    /**
     * 不支持URL
     * desc. 建议删除内容中的URL
     */
    ISV_PARAM_NOT_SUPPORT_URL("SA20222", "isv.PARAM_NOT_SUPPORT_URL", "不支持URL"),

    /**
     * You must specify To.
     * desc. 确少To参数
     */
    MISSING_PARAMETER_OF_TO("SA20223", "MissingParameter.To", "You must specify To."),

    /**
     * You must specify Message.
     * desc. 参数Message缺失
     */
    MISSING_PARAMETER_OF_MESSAGE("SA20224", "MissingParameter.Message", "You must specify Message."),

    /**
     * You are not authorized to perform the operation.
     * desc. 无权限进行此操作
     */
    FORBIDDEN_OPERATION("SA20225", "Forbidden.Operation", "You are not authorized to perform the operation."),

    /**
     * The status of Alibaba Cloud account is invalid.
     * desc. 账号状态不正确
     */
    ACCOUNT_ABNORMAL("SA20226", "Account.Abnormal", "The status of Alibaba Cloud account is invalid."),

    /**
     * The specified Type is invalid.
     * desc. 参数Type无效, 请检查参数值
     */
    INVALID_PARAMETER_OF_TYPE("SA20227", "InvalidParameter.Type", "The specified Type is invalid."),

    /**
     * The specified To is invalid.
     * desc. 参数To无效, 请检查参数值
     */
    INVALID_PARAMETER_OF_TO("SA20228", "InvalidParameter.To", "The specified To is invalid."),

    /**
     * The specified SenderId is invalid.
     * desc. 参数SenderId无效, 请检查参数值
     */
    INVALID_PARAMETER_OF_SENDER_ID("SA20229", "InvalidParameter.SenderId", "The specified SenderId is invalid."),

    /**
     * The specified phone number is invalid.
     * desc. 手机号码无效或者错误
     */
    PHONE_NUMBER_ILLEGAL("SA20230", "PhoneNumber.Illegal", "The specified phone number is invalid."),

    /**
     * The specified From is invalid.
     * desc. 参数From无效, 请检查参数值
     */
    INVALID_PARAMETER_OF_FROM("SA20231", "InvalidParameter.From", "The specified From is invalid."),

    /**
     * The specified ExternalId is invalid.
     * desc. 参数ExternalId无效, 请检查参数值
     */
    INVALID_PARAMETER_OF_EXTERNAL_ID("SA20232", "InvalidParameter.ExternalId", "The specified ExternalId is invalid."),

    /**
     * The specified country code is not supported.
     * desc. 不支持的国家码
     */
    UNSUPPORT_COUNTRY_CODE("SA20233", "Unsupport.CountryCode", "The specified country code is not supported."),

    /**
     * The specified country code is invalid.
     * desc. 不能识别国家码
     */
    UNKNOWN_COUNTRY_CODE("SA20234", "Unknown.CountryCode", "The specified country code is invalid."),

    /**
     * The specified Channel is invalid.
     * desc. 参数Channel无效
     */
    INVALID_PARAMETER_CHANNEL("SA20235", "InvalidParameter.Channel", "The specified Channel is invalid."),

    /**
     * The monthly volume limit is exceeded.
     * desc. 发送量超过月限额
     */
    MONTH_LIMIT_CONTROL("SA20236", "MonthLimitControl", "The monthly volume limit is exceeded."),

    /**
     * The daily volume limit is exceeded.
     * desc. 发送量超过日限额
     */
    DAY_LIMIT_CONTROL("SA20237", "DayLimitControl", "The daily volume limit is exceeded."),

    /**
     * The account is suspended due to an insufficient balance.
     * desc. 账号已停机
     */
    OUT_OF_SERVICE("SA20238", "OutOfService", "The account is suspended due to an insufficient balance."),

    /**
     * The account balance is insufficient.
     * desc. 余额不足
     */
    AMOUNT_NOT_ENOUGH("SA20239", "Amount.NotEnough", "The account balance is insufficient."),

    /**
     * RAM权限DENY
     * desc. 建议联系平台核查原因
     */
    ISP_RAM_PERMISSION_DENY("SA20240", "isp.RAM_PERMISSION_DENY", "RAM权限DENY"),

    /**
     * JSON参数不合法, 只接受字符串值
     * desc. 建议修改JSON参数
     */
    ISV_INVALID_JSON_PARAM("SA20241", "isv.INVALID_JSON_PARAM", "JSON参数不合法,只接受字符串值"),

    /**
     * 未知异常
     */
    UNKONE_ERROR("SA20242", "UNKONE_ERROR", "未知异常");

    /**
     * 枚举值
     */
    private final String value;

    private final String code;

    /**
     * 枚举信息
     */
    private final String reasonPhrase;

    /**
     * 构造器
     *
     * @param value        枚举值
     * @param code         枚举码
     * @param reasonPhrase 枚举信息
     * @since 1.0.0
     */
    AliYunSmsSendErrorEnum(String value, String code, String reasonPhrase) {
        this.value = value;
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public String getCode() {
        return code;
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

    @Nullable
    public static AliYunSmsSendErrorEnum parseCode(String code) {
        AliYunSmsSendErrorEnum[] enumConstants = AliYunSmsSendErrorEnum.class.getEnumConstants();
        for (AliYunSmsSendErrorEnum enumConstant : enumConstants) {
            if (enumConstant.getCode().equals(code)) {
                return enumConstant;
            }
        }
        return null;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static AliYunSmsSendErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, AliYunSmsSendErrorEnum.class);
    }
}
