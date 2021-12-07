package pro.haichuang.framework.sdk.chuanglansms.exception;

import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.chuanglansms.enums.error.ChuangLanSmsSendErrorEnum;

/**
 * 创蓝短信发送自定义异常
 *
 * @author JiYinchuan
 * @see ChuangLanSmsSendErrorEnum
 * @since 1.1.0.211021
 */
public class ChuangLanSmsSendException extends ThirdPartyException {
    private static final long serialVersionUID = -4072127480321453750L;

    public ChuangLanSmsSendException(ChuangLanSmsSendErrorEnum chuangLanSmsSendErrorEnum) {
        super(chuangLanSmsSendErrorEnum);
    }

    public ChuangLanSmsSendException(ChuangLanSmsSendErrorEnum chuangLanSmsSendErrorEnum, String userTip) {
        super(chuangLanSmsSendErrorEnum, userTip);
    }

    public ChuangLanSmsSendException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ChuangLanSmsSendException(String errorCode, String errorMessage, String userTip) {
        super(errorCode, errorMessage, userTip);
    }
}
