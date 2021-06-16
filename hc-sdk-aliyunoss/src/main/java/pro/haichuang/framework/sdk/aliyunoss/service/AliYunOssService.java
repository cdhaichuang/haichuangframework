package pro.haichuang.framework.sdk.aliyunoss.service;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * AliYunOssService
 *
 * @author JiYinchuan
 * @version 1.0
 */
public interface AliYunOssService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param uploadPath      上传相对路径|文件类型
     * @param childrenPath    上传子路径|业务模块名
     * @return 上传后文件路径
     */
    @NonNull
    String upload(@NonNull MultipartFile file, @NonNull String uploadPath, @NonNull String childrenPath);

    /**
     * 上传文件
     *
     * @param files 文件
     * @param uploadPath      上传相对路径|文件类型
     * @param childrenPath    上传子路径|业务模块名
     * @return 上传后文件路径
     */
    @NonNull
    List<String> upload(@NonNull List<MultipartFile> files, @NonNull String uploadPath, @NonNull String childrenPath);

    /**
     * 删除文件
     *
     * @param ossFilePath OSS文件路径
     */
    void deleteObject(@NonNull String ossFilePath);

    /**
     * 删除文件
     *
     * @param ossFilePaths OSS文件路径
     * @return 上传后文件路径
     */
    @NonNull
    List<String> deleteObject(@NonNull List<String> ossFilePaths);

    /**
     * 删除文件
     *
     * @param ossFilePaths OSS文件路径
     * @param quiet        返回模式 [default: false-详细模式, {true: 简单模式(删除失败的文件列表), false: 详细模式(删除成功的文件列表)}]
     * @return 上传后文件路径
     */
    @NonNull
    List<String> deleteObject(@NonNull List<String> ossFilePaths, boolean quiet);

}
