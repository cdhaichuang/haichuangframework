package pro.haichuang.framework.sdk.huaweicloudobs.exception;

import pro.haichuang.framework.base.exception.ApplicationException;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeiCloudObsConfigErrorEnum;

/**
 * 华为云OBS配置自定义异常
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see HuaWeiCloudObsConfigErrorEnum
 * @since 1.0.0.211009
 */
public class HuaWeiCloudObsConfigException extends ApplicationException {
    private static final long serialVersionUID = -1180694591155280803L;

    public HuaWeiCloudObsConfigException(HuaWeiCloudObsConfigErrorEnum huaWeiCloudObsConfigErrorEnum) {
        super(huaWeiCloudObsConfigErrorEnum);
    }

    public HuaWeiCloudObsConfigException(HuaWeiCloudObsConfigErrorEnum huaWeiCloudObsConfigErrorEnum, String userTip) {
        super(huaWeiCloudObsConfigErrorEnum, userTip);
    }
}
