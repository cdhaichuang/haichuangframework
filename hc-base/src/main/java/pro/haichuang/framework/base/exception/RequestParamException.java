package pro.haichuang.framework.base.exception;


import pro.haichuang.framework.base.enums.abnormal.RequestParamAbnormalEnum;

/**
 * 请求参数自定义异常
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class RequestParamException extends ApplicationException {
    private static final long serialVersionUID = -4632443521803372966L;

    public RequestParamException(RequestParamAbnormalEnum requestParamAbnormalEnum) {
        super(requestParamAbnormalEnum);
    }

    public RequestParamException(RequestParamAbnormalEnum requestParamAbnormalEnum, String userTip) {
        super(requestParamAbnormalEnum, userTip);
    }
}
