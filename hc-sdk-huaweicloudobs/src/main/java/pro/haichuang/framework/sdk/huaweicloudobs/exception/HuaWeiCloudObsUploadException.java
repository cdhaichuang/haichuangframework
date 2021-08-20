package pro.haichuang.framework.sdk.huaweicloudobs.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeCloudObsUploadErrorEnum;

/**
 * 阿里云OSS上传自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see HuaWeCloudObsUploadErrorEnum
 * @since 1.0.0
 */
public class HuaWeiCloudObsUploadException extends ApplicationException {
    private static final long serialVersionUID = 8577734487637960381L;

    public HuaWeiCloudObsUploadException(HuaWeCloudObsUploadErrorEnum huaWeCloudObsUploadErrorEnum) {
        super(huaWeCloudObsUploadErrorEnum);
    }

    public HuaWeiCloudObsUploadException(HuaWeCloudObsUploadErrorEnum huaWeCloudObsUploadErrorEnum, String userTip) {
        super(huaWeCloudObsUploadErrorEnum, userTip);
    }
}
