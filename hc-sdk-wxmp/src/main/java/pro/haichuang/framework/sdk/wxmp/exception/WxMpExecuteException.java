package pro.haichuang.framework.sdk.wxmp.exception;

import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.wxmp.enums.error.WxMpExecuteErrorEnum;

/**
 * 微信公众号执行自定义异常
 *
 * @author JiYinchuan
 * @see WxMpExecuteErrorEnum
 * @since 1.1.0.211021
 */
public class WxMpExecuteException extends ThirdPartyException {
    private static final long serialVersionUID = 8611376846892616632L;

    public WxMpExecuteException(WxMpExecuteErrorEnum wxMpExecuteErrorEnum) {
        super(wxMpExecuteErrorEnum);
    }

    public WxMpExecuteException(WxMpExecuteErrorEnum wxMpExecuteErrorEnum, String userTip) {
        super(wxMpExecuteErrorEnum, userTip);
    }

    public WxMpExecuteException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public WxMpExecuteException(String errorCode, String errorMessage, String userTip) {
        super(errorCode, errorMessage, userTip);
    }
}
