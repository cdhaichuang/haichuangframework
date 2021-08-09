package pro.haichuang.framework.sdk.aliyunoss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * AliYunOssService
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public interface AliYunOssService {

    /**
     * 上传文件
     *
     * @param file         文件
     * @param uploadPath   上传相对路径|文件类型
     * @param childrenPath 上传子路径|业务模块名
     * @return 上传后文件路径
     */
    String upload(MultipartFile file, String uploadPath, String childrenPath);

    /**
     * 上传文件
     *
     * @param files        文件
     * @param uploadPath   上传相对路径|文件类型
     * @param childrenPath 上传子路径|业务模块名
     * @return 上传后文件路径
     */
    List<String> upload(List<MultipartFile> files, String uploadPath, String childrenPath);

    /**
     * 删除文件
     *
     * @param ossFilePath OSS文件路径
     */
    void deleteObject(String ossFilePath);

    /**
     * 删除文件
     *
     * @param ossFilePaths OSS文件路径
     * @return 上传后文件路径
     */
    List<String> deleteObject(List<String> ossFilePaths);

    /**
     * 删除文件
     *
     * @param ossFilePaths OSS文件路径
     * @param quiet        返回模式 [default: false-详细模式, {true: 简单模式(删除失败的文件列表), false: 详细模式(删除成功的文件列表)}]
     * @return 上传后文件路径
     */
    List<String> deleteObject(List<String> ossFilePaths, boolean quiet);

}
