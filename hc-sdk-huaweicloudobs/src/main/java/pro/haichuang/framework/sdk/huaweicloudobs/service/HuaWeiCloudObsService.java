package pro.haichuang.framework.sdk.huaweicloudobs.service;

import com.obs.services.model.DeleteObjectsResult;
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
 * <p>该类为 {@code aliyunobs} 第三方操作核心接口, 项目中所有 {@code aliyunobs} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultHuaWeiCloudObsServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see DefaultHuaWeiCloudObsServiceImpl
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface HuaWeiCloudObsService {

    // ========================= Upload =========================

    /**
     * 上传文件
     *
     * @param uploadFile     源文件对象
     * @param uploadTypeEnum 上传文件类型
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    String uploadByMultipart(MultipartFile uploadFile,
                             UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param uploadFile     源文件对象
     * @param newFileName    新文件名
     * @param uploadTypeEnum 上传文件类型
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    String uploadByMultipart(MultipartFile uploadFile, String newFileName,
                             UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param uploadFiles    源文件对象
     * @param uploadTypeEnum 上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    List<String> uploadByMultipart(List<MultipartFile> uploadFiles,
                                   UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param uploadFiles    源文件对象
     * @param newFileNames   新文件名
     * @param uploadTypeEnum 上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName  上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles, LinkedList<String> newFileNames,
                                   UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件绝对路径
     * @param uploadTypeEnum   上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    String uploadByPath(String absoluteFilePath,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件绝对路径
     * @param newFileName      新文件名
     * @param uploadTypeEnum   上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    String uploadByPath(String absoluteFilePath, String newFileName,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件绝对路径集合
     * @param uploadTypeEnum    上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    List<String> uploadByPath(List<String> absoluteFilePaths,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件绝对路径集合
     * @param newFileNames      新文件名
     * @param uploadTypeEnum    上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    List<String> uploadByPath(LinkedList<String> absoluteFilePaths, LinkedList<String> newFileNames,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件对象
     * @param uploadTypeEnum   上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    String uploadByFile(File absoluteFilePath,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePath 源文件对象
     * @param newFileName      新文件名
     * @param uploadTypeEnum   上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName    上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    String uploadByFile(File absoluteFilePath, String newFileName,
                        UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件对象
     * @param uploadTypeEnum    上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    List<String> uploadByFile(List<File> absoluteFilePaths,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    /**
     * 上传文件
     *
     * @param absoluteFilePaths 源文件对象
     * @param newFileNames      新文件名
     * @param uploadTypeEnum    上传主路径, 建议填写业务模块相关名称
     * @param pathOfBizName     上传文件路径|业务模块路径
     * @return 上传后文件路径
     * @since 1.0.0
     */
    List<String> uploadByFile(LinkedList<File> absoluteFilePaths, LinkedList<String> newFileNames,
                              UploadTypeEnum uploadTypeEnum, String... pathOfBizName);

    // ========================= Download =========================

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param obsFilePath OBS文件路径
     * @param request     {@link HttpServletRequest}
     * @param response    {@link HttpServletResponse}
     * @since 1.0.0
     */
    void downloadToResponse(String obsFilePath, HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param obsFilePath OBS文件路径
     * @param fileName    新文件名称, 为空时则为OBS文件名
     * @param request     {@link HttpServletRequest}
     * @param response    {@link HttpServletResponse}
     * @since 1.0.0
     */
    void downloadToResponse(String obsFilePath, @Nullable String fileName,
                            HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载文件至 File 对象
     *
     * @param obsFilePath OBS文件路径
     * @return File对象
     * @since 1.0.0
     */
    File downloadToFile(String obsFilePath);

    /**
     * 下载文件至 File 对象
     *
     * @param obsFilePath OBS文件路径
     * @param outFileName 新文件名称, 为空时则为OBS文件名
     * @return File对象
     * @since 1.0.0
     */
    File downloadToFile(String obsFilePath, @Nullable String outFileName);

    /**
     * 下载文件至 File 对象
     *
     * @param obsFilePath OBS文件路径
     * @param outFile     新文件对象, 为空时则文件名为OBS文件名
     * @return File对象
     * @since 1.0.0
     */
    File downloadToFile(String obsFilePath, @Nullable File outFile);

    // ========================= Delete =========================

    /**
     * 删除文件
     *
     * @param obsFilePath OBS文件路径
     * @since 1.0.0
     */
    void deleteObjectResSuccess(String obsFilePath);

    /**
     * 删除文件
     *
     * @param obsFilePaths OBS文件路径
     * @return 删除成功的文件路径集合
     * @since 1.0.0
     */
    List<DeleteObjectsResult.DeleteObjectResult> deleteObjectResSuccess(Collection<String> obsFilePaths);

    /**
     * 删除文件
     *
     * @param obsFilePaths OBS文件路径
     * @return 删除失败的文件路径集合
     * @since 1.0.0
     */
    List<DeleteObjectsResult.ErrorResult> deleteObjectResError(Collection<String> obsFilePaths);

}
