package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 请求参数异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see pro.haichuang.framework.base.exception.client.RequestParamException
 * @since 1.0.0.211009
 */
public enum RequestParamErrorEnum implements BaseEnum {

    /**
     * 用户请求参数错误
     */
    PARAMETER_ERROR("A0400", "用户请求参数错误"),

    /**
     * 包含非法恶意跳转链接
     */
    CONTAINS_ILLEGAL_LINKS("A0401", "包含非法恶意跳转链接"),

    /**
     * 无效的用户输入
     */
    INVALID_INPUT("A0402", "无效的用户输入"),

    /**
     * 请求必填参数为空
     */
    PARAMETER_EMPTY("A0410", "请求必填参数为空"),

    /**
     * 用户订单号为空
     */
    ORDER_NUMBER_EMPTY("A0411", "用户订单号为空"),

    /**
     * 订购数量为空
     */
    ORDER_QUANTITY_EMPTY("A0412", "订购数量为空"),

    /**
     * 缺少时间戳参数
     */
    TIMESTAMP_PARAMETER_EMPTY("A0413", "缺少时间戳参数"),

    /**
     * 非法的时间戳参数
     */
    ILLEGAL_TIMESTAMP_PARAMETER("A0414", "非法的时间戳参数"),

    /**
     * 请求参数值超出允许的范围
     */
    PARAMETER_EXCEEDS_RANGE("A0420", "请求参数值超出允许的范围"),

    /**
     * 参数格式不匹配
     */
    PARAMETER_FORMAT_NOT_MATCH("A0421", "参数格式不匹配"),

    /**
     * 地址不在服务范围
     */
    ADDRESS_NOT_IN_SERVICE("A0422", "地址不在服务范围"),

    /**
     * 时间不在服务范围
     */
    TIME_NOT_IN_SERVICE("A0423", "时间不在服务范围"),

    /**
     * 金额超出限制
     */
    AMOUNT_EXCEEDS_LIMIT("A0424", "金额超出限制"),

    /**
     * 数量超出限制
     */
    COUNT_EXCEEDS_LIMIT("A0425", "数量超出限制"),

    /**
     * 请求批量处理总个数超出限制
     */
    BATCH_PROCESSING_EXCEEDS_LIMIT("A0426", "请求批量处理总个数超出限制"),

    /**
     * 请求 JSON 解析失败
     */
    JSON_PARSING_FAILED("A0427", "请求 JSON 解析失败"),

    /**
     * 用户输入内容非法
     */
    INPUT_ILLEGAL("A0430", "用户输入内容非法"),

    /**
     * 包含违禁敏感词
     */
    PROHIBITED_SENSITIVE_WORDS("A0431", "包含违禁敏感词"),

    /**
     * 图片包含违禁信息
     */
    PICTURE_PROHIBITED_INFORMATION("A0432", "图片包含违禁信息"),

    /**
     * 文件侵犯版权
     */
    FILE_INFRINGES_COPYRIGHT("A0433", "文件侵犯版权"),

    /**
     * 用户操作异常
     */
    OPERATION_ERROR("A0440", "用户操作异常"),

    /**
     * 用户支付超时
     */
    PAYMENT_TIMEOUT("A0441", "用户支付超时"),

    /**
     * 确认订单超时
     */
    CONFIRM_ORDER_TIMEOUT("A0442", "确认订单超时"),

    /**
     * 订单已关闭
     */
    ORDER_CLOSED("A0443", "订单已关闭");

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
     * @since 1.0.0.211009
     */
    RequestParamErrorEnum(String value, String reasonPhrase) {
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
    public static RequestParamErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, RequestParamErrorEnum.class);
    }
}
