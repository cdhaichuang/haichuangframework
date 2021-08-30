package pro.haichuang.framework.sdk.aliyunoss.service;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.enums.upload.UploadTypeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * AliYunOssService
 *
 * <p>该类为 {@code aliyunoss} 第三方操作核心接口, 项目中所有 {@code aliyunoss} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultAliYunOssServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see DefaultAliYunOssServiceImpl
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface AliYunOssService {

    // ========================= Upload =========================

    /**
     * 上传文件
     *
     * @param uploadFile     源文件对象
     * @param uploadTypeEnum 上传文件类型
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    String uploadByMultipart(MultipartFile uploadFile, UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param uploadFile     源文件对象
     * @param newFileName    新文件名
     * @param uploadTypeEnum 上传文件类型
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    String uploadByMultipart(MultipartFile uploadFile, String newFileName,
                             UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param uploadFiles    源文件对象
     * @param uploadTypeEnum 上传文件类型
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    List<String> uploadByMultipart(List<MultipartFile> uploadFiles,
                                   UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param uploadFiles    源文件对象
     * @param newFileNames   新文件名
     * @param uploadTypeEnum 上传文件类型
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles, LinkedList<String> newFileNames,
                                   UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件绝对路径
     * @param uploadTypeEnum   上传文件类型
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    String uploadByPath(String absoluteFilePath,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件绝对路径
     * @param newFileName      新文件名
     * @param uploadTypeEnum   上传文件类型
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    String uploadByPath(String absoluteFilePath, String newFileName,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件绝对路径集合
     * @param uploadTypeEnum    上传文件类型
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    List<String> uploadByPath(List<String> absoluteFilePaths,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件绝对路径集合
     * @param newFileNames      新文件名
     * @param uploadTypeEnum    上传文件类型
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    List<String> uploadByPath(LinkedList<String> absoluteFilePaths, LinkedList<String> newFileNames,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件对象
     * @param uploadTypeEnum   上传文件类型
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    String uploadByFile(File absoluteFilePath,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件对象
     * @param newFileName      新文件名
     * @param uploadTypeEnum   上传文件类型
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    String uploadByFile(File absoluteFilePath, String newFileName,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件对象
     * @param uploadTypeEnum    上传文件类型
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    List<String> uploadByFile(List<File> absoluteFilePaths,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件对象
     * @param newFileNames      新文件名
     * @param uploadTypeEnum    上传文件类型
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     */
    List<String> uploadByFile(LinkedList<File> absoluteFilePaths, LinkedList<String> newFileNames,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    // ========================= Download =========================

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param ossFilePath OSS文件路径
     * @param request     {@link HttpServletRequest}
     * @param response    {@link HttpServletResponse}
     */
    void downloadToResponse(String ossFilePath, HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param ossFilePath OSS文件路径
     * @param fileName    新文件名称, 为空时则为OSS文件名
     * @param request     {@link HttpServletRequest}
     * @param response    {@link HttpServletResponse}
     */
    void downloadToResponse(String ossFilePath, @Nullable String fileName,
                            HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载文件至 File 对象
     *
     * @param ossFilePath OSS文件路径
     * @return File对象
     */
    File downloadToFile(String ossFilePath);

    /**
     * 下载文件至 File 对象
     *
     * @param ossFilePath OSS文件路径
     * @param fileName    新文件名称, 为空时则为OSS文件名
     * @return File对象
     */
    File downloadToFile(String ossFilePath, @Nullable String fileName);

    /**
     * 下载文件至 File 对象
     *
     * @param ossFilePath OSS文件路径
     * @param file        新文件对象, 为空时则文件名为OSS文件名
     * @return File对象
     */
    File downloadToFile(String ossFilePath, @Nullable File file);

    // ========================= Delete =========================

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
     * @return 删除成功的文件路径集合
     */
    List<String> deleteObject(Collection<String> ossFilePaths);

    /**
     * 删除文件
     *
     * @param ossFilePaths OSS文件路径
     * @param quiet        返回模式
     *                     [default: false-详细模式, {true: 简单模式(删除失败的文件路径集合), false: 详细模式(删除成功的文件路径集合)}]
     * @return 返回结果参考 {@code quiet} 形参注释
     */
    List<String> deleteObject(Collection<String> ossFilePaths, boolean quiet);

}
