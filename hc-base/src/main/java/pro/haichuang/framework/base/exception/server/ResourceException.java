package pro.haichuang.framework.base.exception.server;

import pro.haichuang.framework.base.enums.error.server.ResourceErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 系统资源自定义异常
 *
 * @author JiYinchuan
 * @see ResourceErrorEnum
 * @since 1.1.0.211021
 */
public class ResourceException extends ApplicationException {
    private static final long serialVersionUID = 5657258945670528713L;

    public ResourceException(ResourceErrorEnum resourceErrorEnum) {
        super(resourceErrorEnum);
    }

    public ResourceException(ResourceErrorEnum resourceErrorEnum, String userTip) {
        super(resourceErrorEnum, userTip);
    }
}