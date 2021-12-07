package pro.haichuang.framework.sdk.aliyunsms.exception;

import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsConfigErrorEnum;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsSendErrorEnum;

/**
 * 阿里云短信发送自定义异常
 *
 * @author JiYinchuan
 * @see AliYunSmsConfigErrorEnum
 * @since 1.1.0.211021
 */
public class AliYunSmsSendException extends ThirdPartyException {
    private static final long serialVersionUID = 375443185449226109L;

    public AliYunSmsSendException(AliYunSmsSendErrorEnum aliYunSmsSendErrorEnum) {
        super(aliYunSmsSendErrorEnum);
    }

    public AliYunSmsSendException(AliYunSmsSendErrorEnum aliYunSmsSendErrorEnum, String userTip) {
        super(aliYunSmsSendErrorEnum, userTip);
    }

    public AliYunSmsSendException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public AliYunSmsSendException(String errorCode, String errorMessage, String userTip) {
        super(errorCode, errorMessage, userTip);
    }
}
