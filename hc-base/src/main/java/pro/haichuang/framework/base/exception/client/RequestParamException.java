package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 请求参数自定义异常
 *
 * @author JiYinchuan
 * @see RequestParamErrorEnum
 * @since 1.1.0.211021
 */
public class RequestParamException extends ApplicationException {
    private static final long serialVersionUID = 4612740762019763844L;

    public RequestParamException(RequestParamErrorEnum requestParamErrorEnum) {
        super(requestParamErrorEnum);
    }

    public RequestParamException(RequestParamErrorEnum requestParamErrorEnum, String userTip) {
        super(requestParamErrorEnum, userTip);
    }
}
