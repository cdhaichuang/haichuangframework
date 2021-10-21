package pro.haichuang.framework.base.exception.server;

import pro.haichuang.framework.base.enums.error.server.ExecutionErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 系统执行超时自定义异常
 *
 * @author JiYinchuan
 * @see ExecutionErrorEnum
 * @since 1.1.0.211021
 */
public class ExecutionException extends ApplicationException {
    private static final long serialVersionUID = -8235265562486853178L;

    public ExecutionException(ExecutionErrorEnum executionErrorEnum) {
        super(executionErrorEnum);
    }

    public ExecutionException(ExecutionErrorEnum executionErrorEnum, String userTip) {
        super(executionErrorEnum, userTip);
    }
}