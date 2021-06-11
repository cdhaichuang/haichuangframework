package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.abnormal.client.AuthorityAbnormalEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 访问权限自定义异常
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class AuthorityException extends ApplicationException {
    private static final long serialVersionUID = -862543118175686477L;

    public AuthorityException(AuthorityAbnormalEnum authorityAbnormalEnum) {
        super(authorityAbnormalEnum);
    }

    public AuthorityException(AuthorityAbnormalEnum authorityAbnormalEnum, String userTip) {
        super(authorityAbnormalEnum, userTip);
    }
}
