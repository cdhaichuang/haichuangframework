package pro.haichuang.framework.sdk.aliyunoss.exception;

import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssConfigErrorEnum;

/**
 * 阿里云OSS配置自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see AliYunOssConfigErrorEnum
 * @since 1.0.0.211009
 */
public class AliYunOssConfigException extends ThirdPartyException {
    private static final long serialVersionUID = 2036101395536151559L;

    public AliYunOssConfigException(AliYunOssConfigErrorEnum aliYunOssConfigErrorEnum) {
        super(aliYunOssConfigErrorEnum);
    }

    public AliYunOssConfigException(AliYunOssConfigErrorEnum aliYunOssConfigErrorEnum, String userTip) {
        super(aliYunOssConfigErrorEnum, userTip);
    }
}
