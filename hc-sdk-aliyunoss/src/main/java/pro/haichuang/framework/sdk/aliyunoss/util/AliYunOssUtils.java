package pro.haichuang.framework.sdk.aliyunoss.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GetObjectRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.util.common.UUIDUtils;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssUploadErrorEnum;
import pro.haichuang.framework.sdk.aliyunoss.exception.AliYunOssUploadException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 阿里云OSS工具类
 *
 * <p>该类为 {@code aliyunoss} 相关操作工具类, 提供了对 {@code aliyunoss} 相关操作的封装
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class AliYunOssUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliYunOssUtils.class);
    private static final String LOG_TAG = "[sdk-aliyunoss] AliYunOss工具类";

    // ========================= SingleUpload =========================

    /**
     * 简单上传文件
     *
     * @param uploadFile      源文件对象
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @throws IOException              获取文件流异常
     * @see #baseFileUploadByMultipart(Collection, String, String, String, String, String, String)
     */
    public static String uploadByMultipart(MultipartFile uploadFile, String accessKeyId, String accessKeySecret,
                                           String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException, IOException {
        return uploadByMultipart(uploadFile, null, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 简单上传文件
     *
     * @param uploadFile      源文件对象
     * @param newFileName     新文件名
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @throws IOException              获取文件流异常
     * @see #baseFileUploadByMultipart(Collection, String, String, String, String, String, String)
     */
    public static String uploadByMultipart(MultipartFile uploadFile, @Nullable String newFileName,
                                           String accessKeyId, String accessKeySecret,
                                           String bucketName, String endPoint,
                                           String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException, IOException {
        String uuid = UUIDUtils.Local.get();
        String resultFilePath = baseFileUploadByMultipart(new LinkedList<>(Collections.singletonList(uploadFile)),
                newFileName != null ? new LinkedList<>(Collections.singletonList(newFileName)) : null,
                accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath).get(0);
        LOGGER.info("[{}] 简单上传文件 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG,
                uuid, bucketName, resultFilePath);
        return resultFilePath;
    }

    /**
     * 简单上传文件
     *
     * @param absoluteFilePath 源文件绝对路径
     * @param accessKeyId      AccessKeyId
     * @param accessKeySecret  AccessKeySecret
     * @param bucketName       BucketName
     * @param endPoint         Endpoint地域节点
     * @param uploadBasePath   上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath    上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @see #uploadByFile(Collection, String, String, String, String, String, String)
     */
    public static String uploadByPath(String absoluteFilePath, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        return uploadByPath(absoluteFilePath, null, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 简单上传文件
     *
     * @param absoluteFilePath 源文件绝对路径
     * @param newFileName      新文件名
     * @param accessKeyId      AccessKeyId
     * @param accessKeySecret  AccessKeySecret
     * @param bucketName       BucketName
     * @param endPoint         Endpoint地域节点
     * @param uploadBasePath   上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath    上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @see #uploadByFile(Collection, String, String, String, String, String, String)
     */
    public static String uploadByPath(String absoluteFilePath, @Nullable String newFileName, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        return uploadByFile(new File(absoluteFilePath), newFileName, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 简单上传文件
     *
     * @param uploadFile      源文件对象
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @see #baseFileUploadByFile(Collection, String, String, String, String, String, String)
     */
    public static String uploadByFile(File uploadFile, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        return uploadByFile(uploadFile, null, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 简单上传文件
     *
     * @param uploadFile      源文件对象
     * @param newFileName     新文件名
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @see #baseFileUploadByFile(Collection, String, String, String, String, String, String)
     */
    public static String uploadByFile(File uploadFile, @Nullable String newFileName, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        String uuid = UUIDUtils.Local.get();
        String resultFilePath = baseFileUploadByFile(new LinkedList<>(Collections.singletonList(uploadFile)),
                newFileName != null ? new LinkedList<>(Collections.singletonList(newFileName)) : null,
                accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath).get(0);
        LOGGER.info("[{}] 简单上传文件 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG,
                uuid, bucketName, resultFilePath);
        return resultFilePath;
    }

    // ========================= MultiUpload =========================

    /**
     * 批量上传文件
     *
     * @param uploadFiles     文件对象集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @throws IOException              获取文件流异常
     */
    public static List<String> uploadByMultipart(Collection<MultipartFile> uploadFiles, String accessKeyId, String accessKeySecret,
                                                 String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException, IOException {
        return uploadByMultipart(new LinkedList<>(uploadFiles), null, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 批量上传文件
     *
     * @param uploadFiles     文件对象集合
     * @param newFileNames    新文件名
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @throws IOException              获取文件流异常
     */
    public static List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles,
                                                 @Nullable LinkedList<String> newFileNames,
                                                 String accessKeyId, String accessKeySecret,
                                                 String bucketName, String endPoint,
                                                 String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException, IOException {
        String uuid = UUIDUtils.Local.get();
        List<String> resultFilePaths = baseFileUploadByMultipart(uploadFiles, newFileNames, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
        LOGGER.info("[{}] 批量上传文件 [uuid: {}, bucketName: {}, resultPaths: {}]", LOG_TAG,
                uuid, bucketName, resultFilePaths);
        return resultFilePaths;
    }

    /**
     * 批量上传文件
     *
     * @param absoluteFilePaths 源文件绝对路径集合
     * @param accessKeyId       AccessKeyId
     * @param accessKeySecret   AccessKeySecret
     * @param bucketName        BucketName
     * @param endPoint          Endpoint地域节点
     * @param uploadBasePath    上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath     上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     */
    public static List<String> uploadByPath(Collection<String> absoluteFilePaths,
                                            String accessKeyId, String accessKeySecret,
                                            String bucketName, String endPoint,
                                            String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        return uploadByPath(new LinkedList<>(absoluteFilePaths), null, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 批量上传文件
     *
     * @param absoluteFilePaths 源文件绝对路径集合
     * @param newFileNames      新文件名
     * @param accessKeyId       AccessKeyId
     * @param accessKeySecret   AccessKeySecret
     * @param bucketName        BucketName
     * @param endPoint          Endpoint地域节点
     * @param uploadBasePath    上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath     上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     */
    public static List<String> uploadByPath(LinkedList<String> absoluteFilePaths,
                                            @Nullable LinkedList<String> newFileNames,
                                            String accessKeyId, String accessKeySecret,
                                            String bucketName, String endPoint,
                                            String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        String uuid = UUIDUtils.Local.get();
        List<String> resultFilePaths = uploadByFile(absoluteFilePaths.stream().map(File::new)
                        .collect(LinkedList::new, LinkedList::add, LinkedList::addAll), newFileNames,
                accessKeyId, accessKeySecret, bucketName, endPoint, uploadBasePath, uploadSubPath);
        LOGGER.info("[{}] 批量上传文件 [uuid: {}, bucketName: {}, resultPaths: {}]", LOG_TAG,
                uuid, bucketName, resultFilePaths);
        return resultFilePaths;
    }

    /**
     * 批量上传文件
     *
     * @param uploadFiles     文件对象集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     */
    public static List<String> uploadByFile(Collection<File> uploadFiles, String accessKeyId, String accessKeySecret,
                                            String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        return uploadByFile(new LinkedList<>(uploadFiles), null, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 批量上传文件
     *
     * @param uploadFiles     文件对象集合
     * @param newFileNames    新文件名
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     */
    public static List<String> uploadByFile(LinkedList<File> uploadFiles,
                                            @Nullable LinkedList<String> newFileNames,
                                            String accessKeyId, String accessKeySecret,
                                            String bucketName, String endPoint,
                                            String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        String uuid = UUIDUtils.Local.get();
        List<String> resultFilePaths = baseFileUploadByFile(uploadFiles, newFileNames, accessKeyId, accessKeySecret,
                bucketName, endPoint, uploadBasePath, uploadSubPath);
        LOGGER.info("[{}] 批量上传文件 [uuid: {}, bucketName: {}, resultPaths: {}]", LOG_TAG,
                uuid, bucketName, resultFilePaths);
        return resultFilePaths;
    }

    // ========================= Download =========================

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param ossFilePath     OSS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param request         {@link HttpServletRequest}
     * @param response        {@link HttpServletResponse}
     * @throws IOException 文件流转文件失败
     * @see #downloadToResponse(String, String, String, String, String, String, HttpServletRequest, HttpServletResponse)
     */
    public static void downloadToResponse(String ossFilePath, String accessKeyId, String accessKeySecret,
                                          String bucketName, String endPoint,
                                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        downloadToResponse(ossFilePath, accessKeyId, accessKeySecret, bucketName, endPoint, null, request, response);
    }

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param ossFilePath     OSS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param fileName        新文件名, 为空时则为OSS文件名
     * @param request         {@link HttpServletRequest}
     * @param response        {@link HttpServletResponse}
     * @throws IOException 文件流转文件失败
     */
    public static void downloadToResponse(String ossFilePath, String accessKeyId, String accessKeySecret,
                                          String bucketName, String endPoint, @Nullable String fileName,
                                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        MediaType mediaType;
        String fileBaseName = FilenameUtils.getName(fileName != null && !fileName.isEmpty() ? fileName : ossFilePath);
        File file = downloadToFile(ossFilePath, accessKeyId, accessKeySecret, bucketName, endPoint, fileBaseName);

        ServletContext servletContext = request.getServletContext();
        String mineType = servletContext.getMimeType(fileBaseName);
        try {
            mediaType = MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        response.setContentType(mediaType.getType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(fileBaseName, StandardCharsets.UTF_8).build().toString());
        response.setContentLengthLong(file.length());

        FileUtils.copyFile(file, response.getOutputStream());
    }

    /**
     * 下载文件至 File 对象
     *
     * @param ossFilePath     OSS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @return File对象
     * @see #downloadToFile(String, String, String, String, String, String)
     */
    public static File downloadToFile(String ossFilePath, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint) {
        return downloadToFile(ossFilePath, accessKeyId, accessKeySecret, bucketName, endPoint, "");
    }

    /**
     * 下载文件至 File 对象
     *
     * @param ossFilePath     OSS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param fileName        新文件名, 为空时则为OSS文件名
     * @return File对象
     */
    public static File downloadToFile(String ossFilePath, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint, @Nullable String fileName) {
        File file;
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            ossFilePath = formatFilename(ossFilePath, true);
            String fileBaseName = FilenameUtils.getName(fileName != null && !fileName.isEmpty() ? fileName : ossFilePath);
            file = new File(fileBaseName);
            ossClient.getObject(new GetObjectRequest(bucketName, ossFilePath), file);
            return file;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 下载文件至 File 对象
     *
     * @param ossFilePath     OSS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param file            新文件对象, 为空时则文件名为OSS文件名
     * @return File对象
     */
    public static File downloadToFile(String ossFilePath, String accessKeyId, String accessKeySecret,
                                      String bucketName, String endPoint, @Nullable File file) {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            ossFilePath = formatFilename(ossFilePath, true);
            file = file != null ? file : new File(ossFilePath);
            ossClient.getObject(new GetObjectRequest(bucketName, ossFilePath), file);
            return file;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    // ========================= Delete =========================

    /**
     * 删除文件
     *
     * @param ossFilePath     OSS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     */
    public static void deleteObject(String ossFilePath, String accessKeyId, String accessKeySecret,
                                    String bucketName, String endPoint) {
        String uuid = UUIDUtils.Local.get();
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject(bucketName, formatFilename(ossFilePath, true));
            LOGGER.info("[{}] 删除文件 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG, uuid, bucketName, ossFilePath);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 批量删除文件
     *
     * @param ossFilePaths    OSS文件路径集合
     * @param endPoint        Endpoint地域节点
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @return 删除成功的文件路径集合
     * @see #deleteObject(Collection, boolean, String, String, String, String)
     */
    public static List<String> deleteObject(Collection<String> ossFilePaths, String accessKeyId, String accessKeySecret,
                                            String bucketName, String endPoint) {
        return deleteObject(ossFilePaths, false, accessKeyId, accessKeySecret, bucketName, endPoint);
    }

    /**
     * 批量删除文件
     *
     * @param ossFilePaths    OSS文件路径集合
     * @param quiet           返回模式
     *                        [default: false-详细模式, {true: 简单模式(删除失败的文件路径集合), false: 详细模式(删除成功的文件路径集合)}]
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @return 返回结果参考 {@code quiet} 形参注释
     */
    public static List<String> deleteObject(Collection<String> ossFilePaths, boolean quiet,
                                            String accessKeyId, String accessKeySecret,
                                            String bucketName, String endPoint) {
        String uuid = UUIDUtils.Local.get();
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            List<String> formatOssFilePaths = ossFilePaths.stream().map(path ->
                    formatFilename(path, true)).collect(Collectors.toList());
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(formatOssFilePaths);
            deleteObjectsRequest.setQuiet(quiet);
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
            LOGGER.info("[{}] 删除文件 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG,
                    uuid, bucketName, ossFilePaths);
            return deleteObjectsResult.getDeletedObjects();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    // ========================= Base =========================

    /**
     * 上传文件
     *
     * @param files           源文件对象集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @throws IOException              获取文件流异常
     */
    public static List<String> baseFileUploadByMultipart(Collection<MultipartFile> files,
                                                         String accessKeyId, String accessKeySecret,
                                                         String bucketName, String endPoint,
                                                         String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException, IOException {
        return baseFileUploadByMultipart(new LinkedList<>(files), null,
                accessKeyId, accessKeySecret, bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 上传文件
     *
     * @param uploadFiles     源文件对象集合
     * @param newFileNames    上传文件名称集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径集合
     * @throws AliYunOssUploadException 阿里云文件上传异常
     * @throws IOException              获取文件流异常
     */
    public static List<String> baseFileUploadByMultipart(LinkedList<MultipartFile> uploadFiles,
                                                         @Nullable LinkedList<String> newFileNames,
                                                         String accessKeyId, String accessKeySecret,
                                                         String bucketName, String endPoint,
                                                         String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException, IOException {
        List<String> resultFilePaths = new ArrayList<>();
        OSS ossClient = null;
        try {
            if (uploadFiles.stream().anyMatch(item -> item == null || item.isEmpty())) {
                throw new AliYunOssUploadException(AliYunOssUploadErrorEnum.NOT_EXISTS);
            }
            if (newFileNames != null && newFileNames.size() != uploadFiles.size()) {
                throw new AliYunOssUploadException(AliYunOssUploadErrorEnum.ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH);
            }
            for (int i = 0; i < uploadFiles.size(); i++) {
                ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
                String fileRelativeName = concatFilename(uploadFiles.get(i),
                        newFileNames != null ? newFileNames.get(i) : null, uploadBasePath, uploadSubPath);
                ossClient.putObject(bucketName, fileRelativeName, uploadFiles.get(i).getInputStream());
                String resultFilePath = formatFilename(FilenameUtils.concat("/", fileRelativeName),
                        false);
                resultFilePaths.add(resultFilePath);
            }
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return resultFilePaths;
    }

    /**
     * 上传文件
     *
     * @param uploadFiles     源文件对象集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     */
    public static List<String> baseFileUploadByFile(Collection<File> uploadFiles, String accessKeyId, String accessKeySecret,
                                                    String bucketName, String endPoint, String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        return baseFileUploadByFile(new LinkedList<>(uploadFiles), null,
                accessKeyId, accessKeySecret, bucketName, endPoint, uploadBasePath, uploadSubPath);
    }

    /**
     * 上传文件
     *
     * @param uploadFiles     源文件对象集合
     * @param newFileNames    上传文件名称集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param uploadBasePath  上传主路径, 建议填写业务模块相关名称
     * @param uploadSubPath   上传子路径, 建议填写文件类型
     * @return 上传后的路径
     * @throws AliYunOssUploadException 阿里云文件上传异常
     */
    public static List<String> baseFileUploadByFile(LinkedList<File> uploadFiles,
                                                    @Nullable LinkedList<String> newFileNames,
                                                    String accessKeyId, String accessKeySecret,
                                                    String bucketName, String endPoint,
                                                    String uploadBasePath, String uploadSubPath)
            throws AliYunOssUploadException {
        List<String> resultFilePaths = new ArrayList<>();
        OSS ossClient = null;
        try {
            if (uploadFiles.stream().anyMatch(item -> item == null || !item.exists())) {
                throw new AliYunOssUploadException(AliYunOssUploadErrorEnum.NOT_EXISTS);
            }
            if (uploadFiles.stream().anyMatch(item -> !item.isFile())) {
                throw new AliYunOssUploadException(AliYunOssUploadErrorEnum.NOT_FILE);
            }
            if (newFileNames != null && newFileNames.size() != uploadFiles.size()) {
                throw new AliYunOssUploadException(AliYunOssUploadErrorEnum.ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH);
            }
            for (int i = 0; i < uploadFiles.size(); i++) {
                String fileAbsolutePath = uploadFiles.get(i).getAbsolutePath();
                ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
                String fileRelativeName = concatFilename(fileAbsolutePath,
                        newFileNames != null ? newFileNames.get(i) : null, uploadBasePath, uploadSubPath);
                ossClient.putObject(bucketName, fileRelativeName, new File(fileAbsolutePath));
                String resultFilePath = formatFilename(FilenameUtils.concat("/", fileRelativeName),
                        false);
                resultFilePaths.add(resultFilePath);
            }
            return resultFilePaths;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    // ========================= Common =========================

    /**
     * 拼接文件名
     *
     * @param file           文件
     * @param newFileName    新文件名
     * @param uploadBasePath 上传主路径
     * @param uploadSubPath  上传子路径
     * @return 拼接后的文件名 [上传主路径 + 上传子路径 + 新文件名]
     */
    private static String concatFilename(MultipartFile file, @Nullable String newFileName, String uploadBasePath, String uploadSubPath) {
        String fileOriginalExtensionName = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = fileOriginalExtensionName != null && !fileOriginalExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(".").concat(fileOriginalExtensionName) : UUIDUtils.random();
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
    private static String concatFilename(String filePath, @Nullable String newFileName, String uploadBasePath, String uploadSubPath) {
        String fileExtensionName = FilenameUtils.getExtension(filePath);
        String fileNewName;
        if (newFileName != null && !newFileName.isEmpty()) {
            fileNewName = newFileName;
        } else {
            fileNewName = !fileExtensionName.isEmpty()
                    ? UUIDUtils.random().concat(".").concat(fileExtensionName) : UUIDUtils.random();
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
    private static String formatFilename(String filename, boolean isReplaceFirstSeparator) {
        filename = filename.replaceAll("\\\\", "/").replaceAll("//", "/");
        return isReplaceFirstSeparator && (StringUtils.equals(String.valueOf(filename.charAt(0)), "/"))
                ? filename.substring(1) : filename;
    }
}
