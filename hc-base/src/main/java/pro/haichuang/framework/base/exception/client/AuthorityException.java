package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.AuthorityErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 访问权限自定义异常
 *
 * @author JiYinchuan
 * @see AuthorityErrorEnum
 * @since 1.1.0.211021
 */
public class AuthorityException extends ApplicationException {
    private static final long serialVersionUID = -8157596811164270886L;

    public AuthorityException(AuthorityErrorEnum authorityErrorEnum) {
        super(authorityErrorEnum);
    }

    public AuthorityException(AuthorityErrorEnum authorityErrorEnum, String userTip) {
        super(authorityErrorEnum, userTip);
    }
}
