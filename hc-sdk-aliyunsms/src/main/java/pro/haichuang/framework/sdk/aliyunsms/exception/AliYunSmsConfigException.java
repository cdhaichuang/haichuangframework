package pro.haichuang.framework.sdk.aliyunsms.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsConfigErrorEnum;

/**
 * 阿里云短信配置自定义异常
 *
 * @author JiYinchuan
 * @see AliYunSmsConfigErrorEnum
 * @since 1.1.0.211021
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
