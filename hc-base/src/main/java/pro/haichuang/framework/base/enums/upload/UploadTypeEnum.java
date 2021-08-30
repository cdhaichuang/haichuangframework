package pro.haichuang.framework.base.enums.upload;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 上传类型枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UploadTypeEnum implements BaseEnum {

    /**
     * 图片类型
     */
    IMAGE("image", "图片类型"),

    /**
     * 文件类型
     */
    FILE("file", "文件类型"),

    /**
     * 视频类型
     */
    VIDEO("video", "视频类型"),

    /**
     * 音频类型
     */
    AUDIO("audio", "音频类型"),

    /**
     * 其他类型
     */
    OTHER("other", "其他类型");

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
    UploadTypeEnum(String value, String reasonPhrase) {
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
    public static UploadTypeEnum resolve(String value) {
        return BaseEnum.resolve(value, UploadTypeEnum.class);
    }
}
