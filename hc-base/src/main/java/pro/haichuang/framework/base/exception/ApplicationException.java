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
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 6539659393927269036L;

    public static String DEFAULT_ERROR_USER_TIP = "网络开小差了, 请稍后再试 (╯﹏╰)";

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     * 错误信息
     */
    protected String errorMessage;

    /**
     * 用户提示信息
     */
    protected String userTip;

    /**
     * 错误枚举
     */
    protected BaseEnum baseEnum;

    /**
     * 构造器
     *
     * @param baseEnum 枚举基类
     * @since 1.0.0
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
     * @since 1.0.0
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

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserTip() {
        return userTip;
    }

    public void setUserTip(String userTip) {
        this.userTip = userTip;
    }

    public BaseEnum getBaseEnum() {
        return baseEnum;
    }

    public void setBaseEnum(BaseEnum baseEnum) {
        this.baseEnum = baseEnum;
    }
}
