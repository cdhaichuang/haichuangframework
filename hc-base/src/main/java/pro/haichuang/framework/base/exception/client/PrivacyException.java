package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.PrivacyErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户隐私未授权自定义异常
 *
 * @author JiYinchuan
 * @see PrivacyErrorEnum
 * @since 1.1.0.211021
 */
public class PrivacyException extends ApplicationException {
    private static final long serialVersionUID = 3540368155314315592L;

    public PrivacyException(PrivacyErrorEnum privacyErrorEnum) {
        super(privacyErrorEnum);
    }

    public PrivacyException(PrivacyErrorEnum privacyErrorEnum, String userTip) {
        super(privacyErrorEnum, userTip);
    }
}
