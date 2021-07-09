package pro.haichuang.framework.base.exception.server;

import pro.haichuang.framework.base.enums.error.server.DisasterRecoveryErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 系统容灾功能被触发自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class DisasterRecoveryException extends ApplicationException {
    private static final long serialVersionUID = 5729776012462890776L;

    public DisasterRecoveryException(DisasterRecoveryErrorEnum disasterRecoveryErrorEnum) {
        super(disasterRecoveryErrorEnum);
    }

    public DisasterRecoveryException(DisasterRecoveryErrorEnum disasterRecoveryErrorEnum, String userTip) {
        super(disasterRecoveryErrorEnum, userTip);
    }
}