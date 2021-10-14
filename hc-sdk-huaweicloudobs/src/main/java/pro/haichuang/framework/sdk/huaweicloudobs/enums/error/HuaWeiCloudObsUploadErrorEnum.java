package pro.haichuang.framework.sdk.huaweicloudobs.enums.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;

/**
 * 华为云OBS上传异常枚举
 *
 * <p>该类为华为云OBS上传异常枚举
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public enum HuaWeiCloudObsUploadErrorEnum implements BaseEnum {

    /**
     * 华为云OBS上传异常
     */
    UPLOAD_FILE_ERROR("SB10200", "华为云OBS上传异常"),

    /**
     * 上传文件路径为空
     */
    UPLOAD_PATH_IS_NULL("SB10201", "上传文件路径为空"),

    /**
     * 上传文件类型为空
     */
    UPLOAD_FILE_TYPE_IS_NULL("SB10202", "上传文件类型为空"),

    /**
     * 原始数据异常
     */
    ORIGIN_DATA_ERROR("SB10210", "原始数据异常"),

    /**
     * 上传文件不存在
     */
    NOT_EXISTS("SB10211", "上传文件不存在"),

    /**
     * 必须为文件
     */
    NOT_FILE("SB10212", "上传数据必须为文件"),

    /**
     * 原始数据与文件名长度不匹配
     */
    ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH("SB10213", "原始数据与文件名长度不匹配");

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
     * @since 1.0.0.211009
     */
    HuaWeiCloudObsUploadErrorEnum(String value, String reasonPhrase) {
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
    public static HuaWeiCloudObsUploadErrorEnum resolve(String value) {
        return BaseEnum.resolve(value, HuaWeiCloudObsUploadErrorEnum.class);
    }
}
