package pro.haichuang.framework.base.exception;

/**
 * 第三方自定义异常
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
public class ThirdPartyException extends RuntimeException {
    private static final long serialVersionUID = 1876698766653470982L;

    /**
     * 错误码
     */
    protected String errorCode;

    /**
     * 错误信息
     */
    protected String errorMessage;

    public ThirdPartyException(String errorCode, String errorMessage) {
        super("第三方服务异常: [errorCode: " + errorCode + ", errorMessage: " + errorMessage + "]");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
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
}
