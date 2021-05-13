package pro.haichuang.framework.base.exception;

import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 全局自定义异常基类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -8205556312320530419L;

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     * 错误信息
     */
    protected String errorMessage;

    /**
     * 提示信息
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
     */
    public ApplicationException(BaseEnum baseEnum) {
        super("errorCode: " + baseEnum.value() + ", errorMessage: " + baseEnum.getReasonPhrase());
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.baseEnum = baseEnum;
    }

    /**
     * 构造器
     *
     * @param baseEnum 枚举基类
     * @param userTip  提示信息
     */
    public ApplicationException(BaseEnum baseEnum, String userTip) {
        super("errorCode: " + baseEnum.value() + ", errorMessage: " + baseEnum.getReasonPhrase() + ", userTip: " + userTip);
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.userTip = userTip;
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
