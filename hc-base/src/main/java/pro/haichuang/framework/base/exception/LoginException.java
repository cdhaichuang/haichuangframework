package pro.haichuang.framework.base.exception;


import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户登录自定义异常
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class LoginException extends ApplicationException {
    private static final long serialVersionUID = 351437489895655523L;

    public LoginException(BaseEnum baseEnum) {
        super(baseEnum);
    }

    public LoginException(BaseEnum baseEnum, String userTip) {
        super(baseEnum, userTip);
    }
}
