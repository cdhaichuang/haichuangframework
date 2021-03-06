package pro.haichuang.framework.base.exception.client;


import pro.haichuang.framework.base.enums.error.client.DeviceErrorEnum;
import pro.haichuang.framework.base.exception.ApplicationException;

/**
 * 用户隐私未授权自定义异常
 *
 * @author JiYinchuan
 * @see DeviceErrorEnum
 * @since 1.1.0.211021
 */
public class DeviceException extends ApplicationException {
    private static final long serialVersionUID = 6330999937960360108L;

    public DeviceException(DeviceErrorEnum deviceErrorEnum) {
        super(deviceErrorEnum);
    }

    public DeviceException(DeviceErrorEnum deviceErrorEnum, String userTip) {
        super(deviceErrorEnum, userTip);
    }
}
