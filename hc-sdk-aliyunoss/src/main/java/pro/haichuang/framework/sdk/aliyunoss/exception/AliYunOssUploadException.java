package pro.haichuang.framework.sdk.aliyunoss.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssUploadErrorEnum;

/**
 * 阿里云OSS上传自定义异常
 *
 * @author JiYinchuan
 * @see AliYunOssUploadErrorEnum
 * @since 1.1.0.211021
 */
public class AliYunOssUploadException extends ApplicationException {
    private static final long serialVersionUID = 8577734487637960381L;

    public AliYunOssUploadException(AliYunOssUploadErrorEnum aliYunOssUploadErrorEnum) {
        super(aliYunOssUploadErrorEnum);
    }

    public AliYunOssUploadException(AliYunOssUploadErrorEnum aliYunOssUploadErrorEnum, String userTip) {
        super(aliYunOssUploadErrorEnum, userTip);
    }
}
