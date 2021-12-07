package pro.haichuang.framework.sdk.chuanglansms.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.chuanglansms.enums.error.ChuangLanSmsConfigErrorEnum;

/**
 * 创蓝短信配置自定义异常
 *
 * @author JiYinchuan
 * @see ChuangLanSmsConfigErrorEnum
 * @since 1.1.0.211021
 */
public class ChuangLanSmsConfigException extends ApplicationException {
    private static final long serialVersionUID = 7062871499294033399L;

    public ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum chuangLanSmsConfigErrorEnum) {
        super(chuangLanSmsConfigErrorEnum);
    }

    public ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum chuangLanSmsConfigErrorEnum, String userTip) {
        super(chuangLanSmsConfigErrorEnum, userTip);
    }
}
