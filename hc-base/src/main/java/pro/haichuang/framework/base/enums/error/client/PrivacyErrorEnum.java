package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户隐私未授权异常枚举
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see pro.haichuang.framework.base.exception.client.PrivacyException
 */
public enum PrivacyErrorEnum implements BaseEnum {

    /**
     * 用户隐私未授权异常
     */
    PRIVACY_ERROR("A0900", "用户隐私未授权异常"),

    /**
     * 用户隐私未签署
     */
    PRIVACY_UNSIGNED("A0901", "用户隐私未签署"),

    /**
     * 用户摄像头未授权
     */
    UNAUTHORIZED_CAMERA("A0902", "用户摄像头未授权"),

    /**
     * 用户相机未授权
     */
    CAMERA_NOT_AUTHORIZED("A0903", "用户相机未授权"),

    /**
     * 用户图片库未授权
     */
    ALBUM_NOT_AUTHORIZED("A0904", "用户图片库未授权"),

    /**
     * 用户文件未授权
     */
    FILE_NOT_AUTHORIZED("A0905", "用户文件未授权"),

    /**
     * 用户位置信息未授权
     */
    LOCATION_NOT_AUTHORIZED("A0906", "用户位置信息未授权"),

    /**
     * 用户通讯录未授权
     */
    ADDRESS_BOOK_NOT_AUTHORIZED("A0907", "用户通讯录未授权");

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
    PrivacyErrorEnum(String value, String reasonPhrase) {
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
    public static PrivacyErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, PrivacyErrorEnum.class);
    }
}
