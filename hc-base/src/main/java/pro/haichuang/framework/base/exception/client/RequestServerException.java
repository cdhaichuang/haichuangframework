package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.RequestServerErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 请求服务自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see RequestServerErrorEnum
 * @since 1.0.0
 */
public class RequestServerException extends ApplicationException {
    private static final long serialVersionUID = 4863187527609960249L;

    public RequestServerException(RequestServerErrorEnum requestServerErrorEnum) {
        super(requestServerErrorEnum);
    }

    public RequestServerException(RequestServerErrorEnum requestServerErrorEnum, String userTip) {
        super(requestServerErrorEnum, userTip);
    }
}
