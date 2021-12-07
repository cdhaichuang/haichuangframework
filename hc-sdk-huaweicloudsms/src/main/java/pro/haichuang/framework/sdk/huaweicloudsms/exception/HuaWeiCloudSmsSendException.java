package pro.haichuang.framework.sdk.huaweicloudsms.exception;

import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.error.HuaWeiCloudSmsSendErrorEnum;

/**
 * 华为云对象存储上传自定义异常
 *
 * @author JiYinchuan
 * @see HuaWeiCloudSmsSendErrorEnum
 * @since 1.1.0.211021
 */
public class HuaWeiCloudSmsSendException extends ThirdPartyException {
    private static final long serialVersionUID = 2035444835810848305L;

    public HuaWeiCloudSmsSendException(HuaWeiCloudSmsSendErrorEnum huaWeiCloudSmsSendErrorEnum) {
        super(huaWeiCloudSmsSendErrorEnum);
    }

    public HuaWeiCloudSmsSendException(HuaWeiCloudSmsSendErrorEnum huaWeiCloudSmsSendErrorEnum, String userTip) {
        super(huaWeiCloudSmsSendErrorEnum, userTip);
    }

    public HuaWeiCloudSmsSendException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public HuaWeiCloudSmsSendException(String errorCode, String errorMessage, String userTip) {
        super(errorCode, errorMessage, userTip);
    }
}
