package pro.haichuang.framework.sdk.aliyunoss.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 阿里云OSS上传异常枚举
 *
 * <p>该类为阿里云OSS上传异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
public enum AliYunOssUploadErrorEnum implements BaseEnum {

    /**
     * 上传文件异常
     */
    UPLOAD_FILE_ERROR("SA10200", "上传文件异常"),

    /**
     * 上传文件路径为空
     */
    UPLOAD_PATH_IS_NULL("SA10201", "上传文件路径为空"),

    /**
     * 上传文件类型为空
     */
    UPLOAD_FILE_TYPE_IS_NULL("SA10202", "上传文件类型为空"),

    /**
     * 原始数据异常
     */
    ORIGIN_DATA_ERROR("SA10210", "原始数据异常"),

    /**
     * 上传文件不存在
     */
    NOT_EXISTS("SA10211", "上传文件不存在"),

    /**
     * 必须为文件
     */
    NOT_FILE("SA10212", "上传数据必须为文件"),

    /**
     * 原始数据与文件名长度不匹配
     */
    ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH("SA10213", "原始数据与文件名长度不匹配");

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
     * @since 1.0.0.211014
     */
    AliYunOssUploadErrorEnum(String value, String reasonPhrase) {
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
    public static AliYunOssUploadErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, AliYunOssUploadErrorEnum.class);
    }
}
