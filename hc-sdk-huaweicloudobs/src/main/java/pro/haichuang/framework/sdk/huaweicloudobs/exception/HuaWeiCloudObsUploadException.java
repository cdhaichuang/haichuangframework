package pro.haichuang.framework.sdk.huaweicloudobs.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeiCloudObsUploadErrorEnum;

/**
 * 华为云OBS上传自定义异常
 *
 * @author JiYinchuan
 * @see HuaWeiCloudObsUploadErrorEnum
 * @since 1.1.0.211021
 */
public class HuaWeiCloudObsUploadException extends ApplicationException {
    private static final long serialVersionUID = 8577734487637960381L;

    public HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum huaWeiCloudObsUploadErrorEnum) {
        super(huaWeiCloudObsUploadErrorEnum);
    }

    public HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum huaWeiCloudObsUploadErrorEnum, String userTip) {
        super(huaWeiCloudObsUploadErrorEnum, userTip);
    }
}
