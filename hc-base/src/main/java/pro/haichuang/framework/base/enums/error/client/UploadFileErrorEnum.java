package pro.haichuang.framework.base.enums.error.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 用户上传文件异常枚举
 *
 * @author JiYinchuan
 * @version 1.0
 */
public enum UploadFileErrorEnum implements BaseEnum {

    /**
     * 用户上传文件异常
     */
    UPLOAD_FILE_ERROR("A0700", "用户上传文件异常"),

    /**
     * 用户上传文件类型不匹配
     */
    FILE_TYPE_MISMATCH("A0701", "用户上传文件类型不匹配"),

    /**
     * 用户上传文件太大
     */
    FILE_SIZE_TOO_LARGE("A0702", "用户上传文件太大"),

    /**
     * 用户上传图片太大
     */
    IMAGE_SIZE_TOO_LARGE("A0703", "用户上传图片太大"),

    /**
     * 用户上传视频太大
     */
    VIDEO_SIZE_TOO_LARGE("A0704", "用户上传视频太大"),

    /**
     * 用户上传压缩文件太大
     */
    COMPRESSED_FILE_SIZE_TOO_LARGE("A0705", "用户上传压缩文件太大");

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
    UploadFileErrorEnum(String value, String reasonPhrase) {
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
    public static UploadFileErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, UploadFileErrorEnum.class);
    }
}
