package pro.haichuang.framework.sdk.huaweicloudsms.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.error.HuaWeiCloudSmsSendErrorEnum;

/**
 * 华为云OBS上传自定义异常
 *
 * @author JiYinchuan
 * @see HuaWeiCloudSmsSendErrorEnum
 * @since 1.1.0.211021
 */
public class HuaWeiCloudSmsSendException extends ApplicationException {
    private static final long serialVersionUID = 8577734487637960381L;

    public HuaWeiCloudSmsSendException(HuaWeiCloudSmsSendErrorEnum huaWeiCloudSmsSendErrorEnum) {
        super(huaWeiCloudSmsSendErrorEnum);
    }

    public HuaWeiCloudSmsSendException(HuaWeiCloudSmsSendErrorEnum huaWeiCloudSmsSendErrorEnum, String userTip) {
        super(huaWeiCloudSmsSendErrorEnum, userTip);
    }
}
