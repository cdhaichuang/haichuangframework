package pro.haichuang.framework.sdk.wxmp.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.wxmp.enums.error.WxMpDelayQueueErrorEnum;

/**
 * 微信公众号延时队列自定义异常
 *
 * @author JiYinchuan
 * @see WxMpDelayQueueErrorEnum
 * @since 1.1.0.211021
 */
public class WxMpDelayQueueException extends ApplicationException {
    private static final long serialVersionUID = -1644404103831134880L;

    public WxMpDelayQueueException(WxMpDelayQueueErrorEnum wxMpDelayQueueErrorEnum) {
        super(wxMpDelayQueueErrorEnum);
    }

    public WxMpDelayQueueException(WxMpDelayQueueErrorEnum wxMpDelayQueueErrorEnum, String userTip) {
        super(wxMpDelayQueueErrorEnum, userTip);
    }
}
