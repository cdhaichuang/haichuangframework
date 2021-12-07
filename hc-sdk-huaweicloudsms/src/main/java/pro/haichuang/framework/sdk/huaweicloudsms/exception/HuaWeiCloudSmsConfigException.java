package pro.haichuang.framework.sdk.huaweicloudsms.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.error.HuaWeiCloudSmsConfigErrorEnum;

/**
 * 华为云短信配置自定义异常
 *
 * @author JiYinchuan
 * @see HuaWeiCloudSmsConfigErrorEnum
 * @since 1.1.0.211021
 */
public class HuaWeiCloudSmsConfigException extends ApplicationException {
    private static final long serialVersionUID = -1708143775255731640L;

    public HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum huaWeiCloudSmsConfigErrorEnum) {
        super(huaWeiCloudSmsConfigErrorEnum);
    }

    public HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum huaWeiCloudSmsConfigErrorEnum, String userTip) {
        super(huaWeiCloudSmsConfigErrorEnum, userTip);
    }
}
