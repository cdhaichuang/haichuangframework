package pro.haichuang.framework.base.util.common;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * 文件资源路径工具类
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class FileUriUtils {

    /**
     * 拼接文件名
     *
     * @param file        文件
     * @param newFileName 新文件名
     * @param fileType    文件类型
     * @param uploadPath  上传路径
     * @return 拼接后的文件名 [上传路径 + 文件类型 + 新文件名]
     * @since 1.1.0.211021
     */
    @NonNull
    public static String concatFilename(@NonNull MultipartFile file, @Nullable String newFileName,
                                        @NonNull String fileType, @NonNull String... uploadPath) {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        String fileOriginalExtensionName = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = fileOriginalExtensionName != null && !fileOriginalExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(FilenameUtils.EXTENSION_SEPARATOR_STR).concat(fileOriginalExtensionName)
                    : UUIDUtils.random();
        }
        String relativeUploadPath = FilenameUtils.concat(String.join("/", Arrays.asList(uploadPath)), fileType);
        return formatFilename(FilenameUtils.concat(relativeUploadPath, fileNewName),
                true);
    }

    /**
     * 拼接文件名
     *
     * @param file        文件
     * @param newFileName 新文件名
     * @param fileType    文件类型
     * @param uploadPath  上传路径
     * @return 拼接后的文件名 [上传路径 + 文件类型 + 新文件名]
     * @since 1.1.0.211021
     */
    @NonNull
    public static String concatFilename(@NonNull File file, @Nullable String newFileName,
                                        @NonNull String fileType, @NonNull String... uploadPath) {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        String fileExtensionName = FilenameUtils.getExtension(file.getName());
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = !fileExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(FilenameUtils.EXTENSION_SEPARATOR_STR).concat(fileExtensionName)
                    : UUIDUtils.random();
        }
        String relativeUploadPath = FilenameUtils.concat(String.join("/", Arrays.asList(uploadPath)), fileType);
        return formatFilename(FilenameUtils.concat(relativeUploadPath, fileNewName),
                true);
    }

    /**
     * 拼接文件名
     *
     * @param filePath    文件路径
     * @param newFileName 新文件名
     * @param fileType    文件类型
     * @param uploadPath  上传路径
     * @return 拼接后的文件名 [上传路径 + 文件类型 + 新文件名]
     * @since 1.1.0.211021
     */
    @NonNull
    public static String concatFilename(@NonNull String filePath, @Nullable String newFileName,
                                        @NonNull String fileType, @NonNull String... uploadPath) {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        String fileExtensionName = FilenameUtils.getExtension(filePath);
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = !fileExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(FilenameUtils.EXTENSION_SEPARATOR_STR).concat(fileExtensionName)
                    : UUIDUtils.random();
        }
        String relativeUploadPath = FilenameUtils.concat(String.join("/", Arrays.asList(uploadPath)), fileType);
        return formatFilename(FilenameUtils.concat(relativeUploadPath, fileNewName),
                true);
    }

    /**
     * 格式化文件名
     *
     * @param filename                文件名
     * @param isReplaceFirstSeparator 是否替换第一个分隔符
     * @return 格式化后的文件名
     * @since 1.1.0.211021
     */
    public static String formatFilename(@NonNull String filename, boolean isReplaceFirstSeparator) {
        filename = FilenameUtils.separatorsToUnix(filename);
        return isReplaceFirstSeparator && (StringUtils.equals(String.valueOf(filename.charAt(0)), "/"))
                ? filename.substring(1) : filename;
    }
}
