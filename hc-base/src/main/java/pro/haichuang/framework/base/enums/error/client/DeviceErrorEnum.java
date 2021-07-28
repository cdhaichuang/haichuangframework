package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户设备异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see pro.haichuang.framework.base.exception.client.DeviceException
 */
public enum DeviceErrorEnum implements BaseEnum {

    /**
     * 用户设备异常
     */
    DEVICE_ERROR("A1000", "用户设备异常"),

    /**
     * 用户相机异常
     */
    CAMERA_ERROR("A1001", "用户相机异常"),

    /**
     * 用户麦克风异常
     */
    MICROPHONE_ERROR("A1002", "用户麦克风异常"),

    /**
     * 用户听筒异常
     */
    EARPIECE_ERROR("A1003", "用户听筒异常"),

    /**
     * 用户扬声器异常
     */
    SPEAKER_ERROR("A1004", "用户扬声器异常"),

    /**
     * 用户 GPS 定位异常
     */
    POSITIONING_ERROR("A1005", "用户 GPS 定位异常");

    /**
     * 枚举值
     */
    private final String value;

    /**
     * 枚举信息
     */
    private final String reasonPhrase;

    /**
     * 构造器
     *
     * @param value        枚举值
     * @param reasonPhrase 枚举信息
     */
    DeviceErrorEnum(String value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static DeviceErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, DeviceErrorEnum.class);
    }
}
