package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.ResourceErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户资源自定义异常
 *
 * @author JiYinchuan
 * @see ResourceErrorEnum
 * @since 1.1.0.211021
 */
public class ResourceException extends ApplicationException {
    private static final long serialVersionUID = 5483774629040441247L;

    public ResourceException(ResourceErrorEnum resourceErrorEnum) {
        super(resourceErrorEnum);
    }

    public ResourceException(ResourceErrorEnum resourceErrorEnum, String userTip) {
        super(resourceErrorEnum, userTip);
    }
}
