package pro.haichuang.framework.sdk.aliyunsms.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsConfigErrorEnum;

/**
 * 阿里云SMS配置自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see AliYunSmsConfigErrorEnum
 * @since 1.0.0
 */
public class AliYunSmsConfigException extends ApplicationException {
    private static final long serialVersionUID = -2898514137332412698L;

    public AliYunSmsConfigException(AliYunSmsConfigErrorEnum aliYunSmsConfigErrorEnum) {
        super(aliYunSmsConfigErrorEnum);
    }

    public AliYunSmsConfigException(AliYunSmsConfigErrorEnum aliYunSmsConfigErrorEnum, String userTip) {
        super(aliYunSmsConfigErrorEnum, userTip);
    }
}
