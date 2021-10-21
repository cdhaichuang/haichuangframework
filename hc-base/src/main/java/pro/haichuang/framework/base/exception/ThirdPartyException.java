package pro.haichuang.framework.base.exception;

import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 第三方自定义异常
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class ThirdPartyException extends RuntimeException {
    private static final long serialVersionUID = -2738349034577951728L;

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     * 错误信息
     */
    protected String errorMessage;

    /**
     * 错误信息
     */
    protected String userTip;

    public ThirdPartyException(BaseEnum baseEnum) {
        super("第三方服务异常: [errorCode: " + baseEnum.value() + ", errorMessage: " + baseEnum.getReasonPhrase() + "]");
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.userTip = ApplicationException.DEFAULT_ERROR_USER_TIP;
    }

    public ThirdPartyException(BaseEnum baseEnum, String userTip) {
        super("第三方服务异常: [errorCode: " + baseEnum.value() + ", errorMessage: " + baseEnum.getReasonPhrase() + "]");
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.userTip = userTip;
    }

    public ThirdPartyException(String errorCode, String errorMessage) {
        super("第三方服务异常: [errorCode: " + errorCode + ", errorMessage: " + errorMessage + "]");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userTip = ApplicationException.DEFAULT_ERROR_USER_TIP;
    }

    public ThirdPartyException(String errorCode, String errorMessage, String userTip) {
        super("第三方服务异常: [errorCode: " + errorCode + ", errorMessage: " + errorMessage + "]");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userTip = userTip;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
