package pro.haichuang.framework.sdk.huaweicloudobs.util;

import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.DeleteObjectsRequest;
import com.obs.services.model.DeleteObjectsResult;
import com.obs.services.model.ObsObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.util.common.FileUriUtils;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeiCloudObsUploadErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudobs.exception.HuaWeiCloudObsUploadException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 华为云对象存储工具类
 *
 * <p>该类为 {@code huaweicloudobs} 相关操作工具类, 提供了对 {@code huaweicloudobs} 相关操作的封装
 * <p><a href="https://support.huaweicloud.com/api-obs/zh-cn_topic_0031051947.html">点击查看官方文档</a>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class HuaWeiCloudObsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HuaWeiCloudObsUtils.class);
    private static final String LOG_TAG = "sdk-huaweicloudobs-util";

    /**
     * 简单上传文件
     *
     * @param uploadFile      源文件对象
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   获取文件流异常
     * @see #uploadByMultipart(MultipartFile, String, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static String uploadByMultipart(@NonNull MultipartFile uploadFile,
                                           @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                           @NonNull String bucketName, @NonNull String endPoint,
                                           @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByMultipart(uploadFile, null, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   获取文件流异常
     * @see #baseFileUploadByMultipart(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static String uploadByMultipart(@NonNull MultipartFile uploadFile, @Nullable String newFileName,
                                           @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                           @NonNull String bucketName, @NonNull String endPoint,
                                           @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        String resultFilePath = baseFileUploadByMultipart(new LinkedList<>(Collections.singletonList(uploadFile)),
                newFileName != null ? new LinkedList<>(Collections.singletonList(newFileName)) : null,
                accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath).get(0);
        LOGGER.info("[{}] 简单上传文件 [bucketName: {}, resultPath: {}]", LOG_TAG, bucketName, resultFilePath);
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
     * @param fileType         上传文件类型
     * @param uploadPath       上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #uploadByPath(String, String, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static String uploadByPath(@NonNull String absoluteFilePath,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint,
                                      @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByPath(absoluteFilePath, null, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType         上传文件类型
     * @param uploadPath       上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #uploadByFile(File, String, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static String uploadByPath(@NonNull String absoluteFilePath, @Nullable String newFileName,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint,
                                      @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByFile(new File(absoluteFilePath), newFileName, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
    }

    /**
     * 简单上传文件
     *
     * @param uploadFile      源文件对象
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #uploadByFile(File, String, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static String uploadByFile(@NonNull File uploadFile,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint,
                                      @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByFile(uploadFile, null, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #baseFileUploadByFile(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static String uploadByFile(@NonNull File uploadFile, @Nullable String newFileName,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint,
                                      @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        String resultFilePath = baseFileUploadByFile(new LinkedList<>(Collections.singletonList(uploadFile)),
                newFileName != null ? new LinkedList<>(Collections.singletonList(newFileName)) : null,
                accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath).get(0);
        LOGGER.info("[{}] 简单上传文件 [bucketName: {}, resultPath: {}]", LOG_TAG, bucketName, resultFilePath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   获取文件流异常
     * @see #uploadByMultipart(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> uploadByMultipart(@NonNull Collection<MultipartFile> uploadFiles,
                                                 @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                 @NonNull String bucketName, @NonNull String endPoint,
                                                 @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByMultipart(new LinkedList<>(uploadFiles), null, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   获取文件流异常
     * @see #baseFileUploadByMultipart(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> uploadByMultipart(@NonNull LinkedList<MultipartFile> uploadFiles,
                                                 @Nullable LinkedList<String> newFileNames,
                                                 @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                 @NonNull String bucketName, @NonNull String endPoint,
                                                 @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        if (uploadFiles.isEmpty()) {
            LOGGER.warn("[{}] 批量上传文件为空 [bucketName: {}]", LOG_TAG, bucketName);
            return new ArrayList<>();
        }
        List<String> resultFilePaths = baseFileUploadByMultipart(uploadFiles, newFileNames, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
        LOGGER.info("[{}] 批量上传文件 [bucketName: {}, resultPaths: {}]", LOG_TAG, bucketName, resultFilePaths);
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
     * @param fileType          上传文件类型
     * @param uploadPath        上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #uploadByPath(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> uploadByPath(@NonNull Collection<String> absoluteFilePaths,
                                            @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                            @NonNull String bucketName, @NonNull String endPoint,
                                            @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByPath(new LinkedList<>(absoluteFilePaths), null, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType          上传文件类型
     * @param uploadPath        上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #uploadByFile(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> uploadByPath(@NonNull LinkedList<String> absoluteFilePaths,
                                            @Nullable LinkedList<String> newFileNames,
                                            @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                            @NonNull String bucketName, @NonNull String endPoint,
                                            @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        if (absoluteFilePaths.isEmpty()) {
            LOGGER.warn("[{}] 批量上传文件为空 [bucketName: {}]", LOG_TAG, bucketName);
            return new ArrayList<>();
        }
        List<String> resultFilePaths = uploadByFile(absoluteFilePaths.stream().map(File::new)
                        .collect(LinkedList::new, LinkedList::add, LinkedList::addAll), newFileNames,
                accessKeyId, accessKeySecret, bucketName, endPoint, fileType, uploadPath);
        LOGGER.info("[{}] 批量上传文件 [bucketName: {}, resultPaths: {}]", LOG_TAG, bucketName, resultFilePaths);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #uploadByFile(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> uploadByFile(@NonNull Collection<File> uploadFiles,
                                            @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                            @NonNull String bucketName, @NonNull String endPoint,
                                            @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return uploadByFile(new LinkedList<>(uploadFiles), null, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #baseFileUploadByFile(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> uploadByFile(@NonNull LinkedList<File> uploadFiles,
                                            @Nullable LinkedList<String> newFileNames,
                                            @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                            @NonNull String bucketName, @NonNull String endPoint,
                                            @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        if (uploadFiles.isEmpty()) {
            LOGGER.warn("[{}] 批量上传文件为空 [bucketName: {}]", LOG_TAG, bucketName);
            return new ArrayList<>();
        }
        List<String> resultFilePaths = baseFileUploadByFile(uploadFiles, newFileNames, accessKeyId, accessKeySecret,
                bucketName, endPoint, fileType, uploadPath);
        LOGGER.info("[{}] 批量上传文件 [bucketName: {}, resultPaths: {}]", LOG_TAG, bucketName, resultFilePaths);
        return resultFilePaths;
    }

    // ========================= Download =========================

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param obsFilePath     OBS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param request         {@link HttpServletRequest}
     * @param response        {@link HttpServletResponse}
     * @throws IOException 文件流转文件失败
     * @see #downloadToResponse(String, String, String, String, String, String, HttpServletRequest, HttpServletResponse)
     * @since 1.1.0.211021
     */
    public static void downloadToResponse(@NonNull String obsFilePath,
                                          @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                          @NonNull String bucketName, @NonNull String endPoint,
                                          @NonNull HttpServletRequest request, @NonNull HttpServletResponse response)
            throws IOException {
        downloadToResponse(obsFilePath, accessKeyId, accessKeySecret, bucketName, endPoint, null, request, response);
    }

    /**
     * 下载文件至 HttpServletResponse
     *
     * @param obsFilePath     OBS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param outFileName     新文件名, 为空时则为OBS文件名
     * @param request         {@link HttpServletRequest}
     * @param response        {@link HttpServletResponse}
     * @throws IOException 文件流转文件失败
     * @since 1.1.0.211021
     */
    public static void downloadToResponse(@NonNull String obsFilePath,
                                          @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                          @NonNull String bucketName, @NonNull String endPoint,
                                          @Nullable String outFileName,
                                          @NonNull HttpServletRequest request, @NonNull HttpServletResponse response)
            throws IOException {
        MediaType mediaType;
        String fileBaseName = FilenameUtils.getName(outFileName != null && !outFileName.isEmpty() ? outFileName : obsFilePath);
        File file = downloadToFile(obsFilePath, accessKeyId, accessKeySecret, bucketName, endPoint, fileBaseName);

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
     * @param obsFilePath     OBS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @return File对象
     * @throws IOException 文件流转文件失败|关闭 {@code obs} 连接异常
     * @see #downloadToFile(String, String, String, String, String, String)
     * @since 1.1.0.211021
     */
    @NonNull
    public static File downloadToFile(@NonNull String obsFilePath,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint)
            throws IOException {
        return downloadToFile(obsFilePath, accessKeyId, accessKeySecret, bucketName, endPoint, "");
    }

    /**
     * 下载文件至 File 对象
     *
     * @param obsFilePath     OBS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param outFileName     新文件名, 为空时则为OBS文件名
     * @return File对象
     * @throws IOException 文件流转文件失败|关闭 {@code obs} 连接异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static File downloadToFile(@NonNull String obsFilePath,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint,
                                      @Nullable String outFileName)
            throws IOException {
        File outFile;
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            obsFilePath = FileUriUtils.formatFilename(obsFilePath, true);
            String fileBaseName = FilenameUtils.getName(outFileName != null && !outFileName.isEmpty() ? outFileName : obsFilePath);
            outFile = new File(fileBaseName);
            ObsObject obsObject = obsClient.getObject(bucketName, obsFilePath);
            try (InputStream inputStream = obsObject.getObjectContent()) {
                FileUtils.copyToFile(inputStream, outFile);
            }
            return outFile;
        }
    }

    /**
     * 下载文件至 File 对象
     *
     * @param obsFilePath     OBS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @param outFile         新文件对象, 为空时则文件名为OBS文件名
     * @return File对象
     * @throws IOException 文件流转文件失败|关闭 {@code obs} 连接异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static File downloadToFile(@NonNull String obsFilePath,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String endPoint,
                                      @Nullable File outFile)
            throws IOException {
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            obsFilePath = FileUriUtils.formatFilename(obsFilePath, true);
            outFile = outFile != null ? outFile : new File(obsFilePath);
            ObsObject obsObject = obsClient.getObject(bucketName, obsFilePath);
            try (InputStream inputStream = obsObject.getObjectContent()) {
                FileUtils.copyToFile(inputStream, outFile);
            }
            return outFile;
        }
    }

    // ========================= Delete =========================

    /**
     * 删除文件
     *
     * @param obsFilePath     OBS文件路径
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @throws IOException 文件流转文件失败|关闭 {@code obs} 连接异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static void deleteObject(@NonNull String obsFilePath,
                                    @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                    @NonNull String bucketName, @NonNull String endPoint)
            throws IOException {
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            obsClient.deleteObject(bucketName, FileUriUtils.formatFilename(obsFilePath, true));
            LOGGER.info("[{}] 删除文件 [bucketName: {}, resultPath: {}]", LOG_TAG, bucketName, obsFilePath);
        }
    }

    /**
     * 批量删除文件
     *
     * @param obsFilePaths    OBS文件路径集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @return 删除失败的文件路径集合
     * @throws IOException 关闭 {@code obs} 连接异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<DeleteObjectsResult.ErrorResult> deleteObjectResError(@NonNull Collection<String> obsFilePaths,
                                                                             @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                                             @NonNull String bucketName, @NonNull String endPoint)
            throws IOException {
        if (obsFilePaths.isEmpty()) {
            LOGGER.warn("[{}] 批量删除文件为空 [bucketName: {}]", LOG_TAG, bucketName);
            return new ArrayList<>();
        }
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
            obsFilePaths.stream()
                    .map(path -> FileUriUtils.formatFilename(path, true))
                    .forEach(deleteObjectsRequest::addKeyAndVersion);
            DeleteObjectsResult deleteObjectsResult = obsClient.deleteObjects(deleteObjectsRequest);
            List<DeleteObjectsResult.ErrorResult> errorResults = deleteObjectsResult.getErrorResults();
            LOGGER.info("[{}] 删除文件 [bucketName: {}, errorResults: {}]", LOG_TAG, bucketName, JSONObject.toJSONString(errorResults));
            return errorResults;
        }
    }

    /**
     * 批量删除文件
     *
     * @param obsFilePaths    OBS文件路径集合
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param endPoint        Endpoint地域节点
     * @return 删除成功的文件路径集合
     * @throws IOException 关闭 {@code obs} 连接异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<DeleteObjectsResult.DeleteObjectResult> deleteObjectResSuccess(@NonNull Collection<String> obsFilePaths,
                                                                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                                                      @NonNull String bucketName, @NonNull String endPoint)
            throws IOException {
        if (obsFilePaths.isEmpty()) {
            LOGGER.warn("[{}] 批量删除文件为空 [bucketName: {}]", LOG_TAG, bucketName);
            return new ArrayList<>();
        }
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
            obsFilePaths.stream()
                    .map(path -> FileUriUtils.formatFilename(path, true))
                    .forEach(deleteObjectsRequest::addKeyAndVersion);
            DeleteObjectsResult deleteObjectsResult = obsClient.deleteObjects(deleteObjectsRequest);
            List<DeleteObjectsResult.DeleteObjectResult> successResults = deleteObjectsResult.getDeletedObjectResults()
                    .stream().filter(DeleteObjectsResult.DeleteObjectResult::isDeleteMarker).collect(Collectors.toList());
            LOGGER.info("[{}] 批量删除文件 [bucketName: {}, successResults: {}]", LOG_TAG, bucketName, JSONObject.toJSONString(successResults));
            return successResults;
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   获取文件流异常
     * @see #baseFileUploadByMultipart(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> baseFileUploadByMultipart(@NonNull Collection<MultipartFile> files,
                                                         @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                         @NonNull String bucketName, @NonNull String endPoint,
                                                         @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return baseFileUploadByMultipart(new LinkedList<>(files), null,
                accessKeyId, accessKeySecret, bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径集合 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   获取文件流异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> baseFileUploadByMultipart(@NonNull LinkedList<MultipartFile> uploadFiles,
                                                         @Nullable LinkedList<String> newFileNames,
                                                         @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                         @NonNull String bucketName, @NonNull String endPoint,
                                                         @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        if (uploadFiles.stream().anyMatch(item -> item == null || item.isEmpty())) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.NOT_EXISTS);
        }
        if (newFileNames != null && newFileNames.size() != uploadFiles.size()) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH);
        }
        List<String> resultFilePaths = new ArrayList<>();
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            for (int i = 0; i < uploadFiles.size(); i++) {
                String fileRelativeName = FileUriUtils.concatFilename(uploadFiles.get(i),
                        newFileNames != null ? newFileNames.get(i) : null, fileType, uploadPath);
                obsClient.putObject(bucketName, fileRelativeName, uploadFiles.get(i).getInputStream());
                String resultFilePath = FileUriUtils.formatFilename(FilenameUtils.concat("/", fileRelativeName),
                        false);
                resultFilePaths.add(resultFilePath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @see #baseFileUploadByFile(LinkedList, LinkedList, String, String, String, String, String, String...)
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> baseFileUploadByFile(@NonNull Collection<File> uploadFiles,
                                                    @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                    @NonNull String bucketName, @NonNull String endPoint,
                                                    @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        return baseFileUploadByFile(new LinkedList<>(uploadFiles), null,
                accessKeyId, accessKeySecret, bucketName, endPoint, fileType, uploadPath);
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
     * @param fileType        上传文件类型
     * @param uploadPath      上传路径
     * @return 上传后的路径 [上传路径 + 文件类型 + 文件名]
     * @throws HuaWeiCloudObsUploadException 华为云文件上传异常
     * @throws IOException                   关闭 {@code obs} 连接异常
     * @since 1.1.0.211021
     */
    @NonNull
    public static List<String> baseFileUploadByFile(@NonNull LinkedList<File> uploadFiles,
                                                    @Nullable LinkedList<String> newFileNames,
                                                    @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                                    @NonNull String bucketName, @NonNull String endPoint,
                                                    @NonNull String fileType, @NonNull String... uploadPath)
            throws HuaWeiCloudObsUploadException, IOException {
        Assert.notEmpty(uploadPath, "上传路径不能为空");
        if (uploadFiles.stream().anyMatch(item -> item == null || !item.exists())) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.NOT_EXISTS);
        }
        if (uploadFiles.stream().anyMatch(item -> !item.isFile())) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.NOT_FILE);
        }
        if (newFileNames != null && newFileNames.size() != uploadFiles.size()) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.ORIGIN_DATA_AND_FILE_NAME_SIZE_MISMATCH);
        }
        List<String> resultFilePaths = new ArrayList<>();
        try (ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endPoint)) {
            for (int i = 0; i < uploadFiles.size(); i++) {
                String fileAbsolutePath = uploadFiles.get(i).getAbsolutePath();
                String fileRelativeName = FileUriUtils.concatFilename(fileAbsolutePath,
                        newFileNames != null ? newFileNames.get(i) : null, fileType, uploadPath);
                obsClient.putObject(bucketName, fileRelativeName, new File(fileAbsolutePath));
                String resultFilePath = FileUriUtils.formatFilename(FilenameUtils.concat("/", fileRelativeName),
                        false);
                resultFilePaths.add(resultFilePath);
            }
            return resultFilePaths;
        }
    }
}
