package pro.haichuang.framework.base.util.common;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件资源路径工具类
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class FileUriUtils {

    /**
     * 拼接文件名
     *
     * @param file           文件
     * @param newFileName    新文件名
     * @param uploadBasePath 上传主路径
     * @param uploadSubPath  上传子路径
     * @return 拼接后的文件名 [上传主路径 + 上传子路径 + 新文件名]
     */
    public static String concatFilename(MultipartFile file, @Nullable String newFileName, String uploadBasePath, String uploadSubPath) {
        String fileOriginalExtensionName = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = fileOriginalExtensionName != null && !fileOriginalExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(FilenameUtils.EXTENSION_SEPARATOR_STR).concat(fileOriginalExtensionName)
                    : UUIDUtils.random();
        }
        return formatFilename(FilenameUtils.concat(FilenameUtils.concat(uploadBasePath, uploadSubPath), fileNewName),
                true);
    }

    /**
     * 拼接文件名
     *
     * @param filePath       文件路径
     * @param newFileName    新文件名
     * @param uploadBasePath 上传主路径
     * @param uploadSubPath  上传子路径
     * @return 拼接后的文件名 [上传主路径 + 上传子路径 + 新文件名]
     */
    public static String concatFilename(String filePath, @Nullable String newFileName, String uploadBasePath, String uploadSubPath) {
        String fileExtensionName = FilenameUtils.getExtension(filePath);
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = !fileExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(FilenameUtils.EXTENSION_SEPARATOR_STR).concat(fileExtensionName)
                    : UUIDUtils.random();
        }
        return formatFilename(FilenameUtils.concat(FilenameUtils.concat(uploadBasePath, uploadSubPath), fileNewName),
                true);
    }

    /**
     * 格式化文件名
     *
     * @param filename                文件名
     * @param isReplaceFirstSeparator 是否替换第一个分隔符
     * @return 格式化后的文件名
     */
    public static String formatFilename(String filename, boolean isReplaceFirstSeparator) {
        filename = filename.replaceAll("\\\\", "/").replaceAll("//", "/");
        return isReplaceFirstSeparator && (StringUtils.equals(String.valueOf(filename.charAt(0)), "/"))
                ? filename.substring(1) : filename;
    }
}
