package pro.haichuang.framework.sdk.huaweicloudobs.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeCloudObsConfigErrorEnum;

/**
 * 华为云OBS配置自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see HuaWeCloudObsConfigErrorEnum
 * @since 1.0.0
 */
public class HuaWeiCloudObsConfigException extends ApplicationException {
    private static final long serialVersionUID = -1180694591155280803L;

    public HuaWeiCloudObsConfigException(HuaWeCloudObsConfigErrorEnum huaWeCloudObsConfigErrorEnum) {
        super(huaWeCloudObsConfigErrorEnum);
    }

    public HuaWeiCloudObsConfigException(HuaWeCloudObsConfigErrorEnum huaWeCloudObsConfigErrorEnum, String userTip) {
        super(huaWeCloudObsConfigErrorEnum, userTip);
    }
}
