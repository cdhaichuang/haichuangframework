package pro.haichuang.framework.base.exception.server;

import pro.haichuang.framework.base.enums.error.server.ResourceErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 系统执行超时自定义异常
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class ExecutionException extends ApplicationException {
    private static final long serialVersionUID = 2702182334838015686L;

    public ExecutionException(ResourceErrorEnum resourceErrorEnum) {
        super(resourceErrorEnum);
    }

    public ExecutionException(ResourceErrorEnum resourceErrorEnum, String userTip) {
        super(resourceErrorEnum, userTip);
    }
}