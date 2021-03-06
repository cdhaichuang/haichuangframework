package pro.haichuang.framework.sdk.wxmp.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.wxmp.enums.error.WxMpConfigErrorEnum;

/**
 * 微信公众号配置自定义异常
 *
 * @author JiYinchuan
 * @see WxMpConfigErrorEnum
 * @since 1.1.0.211021
 */
public class WxMpConfigException extends ApplicationException {
    private static final long serialVersionUID = 8611376846892616632L;

    public WxMpConfigException(WxMpConfigErrorEnum wxMpConfigErrorEnum) {
        super(wxMpConfigErrorEnum);
    }

    public WxMpConfigException(WxMpConfigErrorEnum wxMpConfigErrorEnum, String userTip) {
        super(wxMpConfigErrorEnum, userTip);
    }
}
