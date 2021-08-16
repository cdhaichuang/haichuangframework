package pro.haichuang.framework.sdk.aliyunoss.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssConfigErrorEnum;

/**
 * 阿里云OSS配置自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see AliYunOssConfigErrorEnum
 * @since 1.0.0
 */
public class AliYunOssConfigException extends ApplicationException {
    private static final long serialVersionUID = -1180694591155280803L;

    public AliYunOssConfigException(AliYunOssConfigErrorEnum aliYunOssConfigErrorEnum) {
        super(aliYunOssConfigErrorEnum);
    }

    public AliYunOssConfigException(AliYunOssConfigErrorEnum aliYunOssConfigErrorEnum, String userTip) {
        super(aliYunOssConfigErrorEnum, userTip);
    }
}
