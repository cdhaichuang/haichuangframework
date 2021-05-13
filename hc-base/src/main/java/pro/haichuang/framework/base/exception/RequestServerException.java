package pro.haichuang.framework.base.exception;


import pro.haichuang.framework.base.enums.abnormal.RequestServerAbnormalEnum;

/**
 * 请求服务自定义异常
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class RequestServerException extends ApplicationException {
    private static final long serialVersionUID = -6311758362810869067L;

    public RequestServerException(RequestServerAbnormalEnum requestServerAbnormalEnum) {
        super(requestServerAbnormalEnum);
    }

    public RequestServerException(RequestServerAbnormalEnum requestServerAbnormalEnum, String userTip) {
        super(requestServerAbnormalEnum, userTip);
    }
}
