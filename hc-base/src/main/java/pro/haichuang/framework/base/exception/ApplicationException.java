package pro.haichuang.framework.base.exception;

import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 全局自定义异常基类
 *
 * <p>该类为全局自定义异常基类, 为避免部分功能失效, 项目中所有的自定义异常原则上都必须继承该类
 * 目前该类主要关联说明:
 * <ul>
 *     <li>全局Controller异常处理: {@link pro.haichuang.framework.base.config.mvc.BaseControllerAdvice}</li>
 *     <li>验证工具类: {@link pro.haichuang.framework.base.util.common.ValidateUtils}</li>
 * </ul>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class ApplicationException extends RuntimeException {
    public static final String DEFAULT_ERROR_USER_TIP = "网络开小差了, 请稍后再试 (╯﹏╰)";

    private static final long serialVersionUID = 5592697530081508972L;

    /**
     * 错误码
     */
    protected final String errorCode;

    /**
     * 错误信息
     */
    protected final String errorMessage;

    /**
     * 用户提示信息
     */
    protected final String userTip;

    /**
     * 错误枚举
     */
    protected final BaseEnum baseEnum;

    /**
     * 构造器
     *
     * @param baseEnum 枚举基类
     * @since 1.1.0.211021
     */
    public ApplicationException(BaseEnum baseEnum) {
        super("errorCode: " + baseEnum.value() + ", errorMessage: " + baseEnum.getReasonPhrase());
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.baseEnum = baseEnum;
        userTip = DEFAULT_ERROR_USER_TIP;
    }

    /**
     * 构造器
     *
     * @param baseEnum 枚举基类
     * @param userTip  用户提示信息
     * @since 1.1.0.211021
     */
    public ApplicationException(BaseEnum baseEnum, @Nullable String userTip) {
        super("errorCode: " + baseEnum.value() + ", errorMessage: " + baseEnum.getReasonPhrase() + ", userTip: " + userTip);
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.userTip = userTip == null || userTip.isEmpty() ? DEFAULT_ERROR_USER_TIP : userTip;
        this.baseEnum = baseEnum;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserTip() {
        return userTip;
    }

    public BaseEnum getBaseEnum() {
        return baseEnum;
    }
}
