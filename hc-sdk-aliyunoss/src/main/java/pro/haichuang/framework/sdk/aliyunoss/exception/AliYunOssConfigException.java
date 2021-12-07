package pro.haichuang.framework.sdk.aliyunoss.exception;

import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssConfigErrorEnum;

/**
 * 阿里云对象存储配置自定义异常
 *
 * @author JiYinchuan
 * @see AliYunOssConfigErrorEnum
 * @since 1.1.0.211021
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
