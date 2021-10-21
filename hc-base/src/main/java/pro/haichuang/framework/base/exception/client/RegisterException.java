package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.RegisterErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户注册自定义异常
 *
 * @author JiYinchuan
 * @see RegisterErrorEnum
 * @since 1.1.0.211021
 */
public class RegisterException extends ApplicationException {
    private static final long serialVersionUID = 1376920858643859913L;

    public RegisterException(RegisterErrorEnum registerErrorEnum) {
        super(registerErrorEnum);
    }

    public RegisterException(RegisterErrorEnum registerErrorEnum, String userTip) {
        super(registerErrorEnum, userTip);
    }
}
