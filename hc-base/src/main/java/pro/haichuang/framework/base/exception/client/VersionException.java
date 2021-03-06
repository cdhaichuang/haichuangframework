package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.VersionErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户当前版本自定义异常
 *
 * @author JiYinchuan
 * @see VersionErrorEnum
 * @since 1.1.0.211021
 */
public class VersionException extends ApplicationException {
    private static final long serialVersionUID = 4058423515389560860L;

    public VersionException(VersionErrorEnum versionErrorEnum) {
        super(versionErrorEnum);
    }

    public VersionException(VersionErrorEnum versionErrorEnum, String userTip) {
        super(versionErrorEnum, userTip);
    }
}
