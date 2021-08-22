package pro.haichuang.framework.sdk.huaweicloudobs.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 华为云OBS上传异常枚举
 *
 * <p>该类为华为云OBS上传异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum HuaWeCloudObsUploadErrorEnum implements BaseEnum {

    /**
     * 上传文件异常
     */
    UPLOAD_FILE_ERROR("H0200", "上传文件异常"),

    /**
     * 上传文件主路径为空
     */
    UPLOAD_BASE_PATH_IS_NULL("H0201", "上传文件主路径为空"),

    /**
     * 上传文件子路径为空
     */
    UPLOAD_SUB_PATH_IS_NULL("H0202", "上传文件子路径为空"),


    /**
     * 原始数据异常
     */
    ORIGIN_DATA_ERROR("H0210", "原始数据异常"),

    /**
     * 上传文件不存在
     */
    NOT_EXISTS("H0211", "上传文件不存在"),

    /**
     * 必须为文件
     */
    NOT_FILE("H0212", "上传数据必须为文件"),

    /**
     * 原始数据与文件名长度不匹配
     */
    ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH("H0213", "原始数据与文件名长度不匹配");

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
    HuaWeCloudObsUploadErrorEnum(String value, String reasonPhrase) {
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
    public static HuaWeCloudObsUploadErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, HuaWeCloudObsUploadErrorEnum.class);
    }
}
