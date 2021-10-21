package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.UploadFileErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户上传文件自定义异常
 *
 * @author JiYinchuan
 * @see UploadFileErrorEnum
 * @since 1.1.0.211021
 */
public class UploadFileException extends ApplicationException {
    private static final long serialVersionUID = -5150359400314378661L;

    public UploadFileException(UploadFileErrorEnum uploadFileErrorEnum) {
        super(uploadFileErrorEnum);
    }

    public UploadFileException(UploadFileErrorEnum uploadFileErrorEnum, String userTip) {
        super(uploadFileErrorEnum, userTip);
    }
}
