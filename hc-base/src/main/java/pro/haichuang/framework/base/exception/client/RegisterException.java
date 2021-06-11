package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.abnormal.client.RegisterAbnormalEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户注册自定义异常
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class RegisterException extends ApplicationException {
    private static final long serialVersionUID = 4479131057139745095L;

    public RegisterException(RegisterAbnormalEnum registerAbnormalEnum) {
        super(registerAbnormalEnum);
    }

    public RegisterException(RegisterAbnormalEnum registerAbnormalEnum, String userTip) {
        super(registerAbnormalEnum, userTip);
    }
}
