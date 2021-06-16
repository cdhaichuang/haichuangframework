package pro.haichuang.framework.sdk.aliyunoss.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.OSSObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.util.common.UUIDUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 阿里云OSS工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class AliYunOssUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliYunOssUtils.class);
    private static final String LOG_TAG = "AliYunOss工具类";

    /**
     * 上传文件
     *
     * @param file            文件对象
     * @param endPoint        Endpoint
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param uploadPath      上传相对路径|文件类型
     * @param childrenPath    上传子路径|业务模块名
     * @return 上传后的路径
     * @throws IOException 获取文件流异常
     */
    @NonNull
    public static String upload(@NonNull MultipartFile file, @NonNull String endPoint,
                                @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                @NonNull String bucketName, @NonNull String uploadPath, @NonNull String childrenPath) throws IOException {
        String uuid = UUIDUtils.Local.get();
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            String fileRelativeName = concatFilename(file, uploadPath, childrenPath);
            ossClient.putObject(bucketName, fileRelativeName, file.getInputStream());
            String resultFilePath = formatFilename(FilenameUtils.concat("/", fileRelativeName), false);
            LOGGER.info("[{}] 简单上传 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG, uuid, bucketName, resultFilePath);
            return resultFilePath;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 上传文件
     *
     * @param absoluteFilePath 文件绝对路径
     * @param endPoint         Endpoint
     * @param accessKeyId      AccessKeyId
     * @param accessKeySecret  AccessKeySecret
     * @param bucketName       BucketName
     * @param uploadPath       上传相对路径|文件类型
     * @param childrenPath     上传子路径|业务模块名
     * @return 上传后的路径
     * @throws FileNotFoundException 文件不存在异常
     */
    @NonNull
    public static String upload(@NonNull String absoluteFilePath, @NonNull String endPoint,
                                @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                @NonNull String bucketName, @NonNull String uploadPath, @NonNull String childrenPath) throws FileNotFoundException {
        String uuid = UUIDUtils.Local.get();
        OSS ossClient = null;
        try {
            File uploadFile = new File(absoluteFilePath);
            if (!uploadFile.exists()) {
                throw new FileNotFoundException(String.format("[%s] 文件不存在", absoluteFilePath));
            }
            if (!uploadFile.isFile()) {
                throw new FileNotFoundException(String.format("[%s] 文件路径错误", absoluteFilePath));
            }
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            String fileRelativeName = concatFilename(absoluteFilePath, uploadPath, childrenPath);
            ossClient.putObject(bucketName, fileRelativeName, new File(absoluteFilePath));
            String resultFilePath = formatFilename(FilenameUtils.concat("/", fileRelativeName), false);
            LOGGER.info("[{}] 简单上传 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG, uuid, bucketName, resultFilePath);
            return resultFilePath;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 批量上传文件
     *
     * @param files           文件对象集合
     * @param endPoint        Endpoint
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @param uploadPath      上传相对路径|文件类型
     * @param childrenPath    上传子路径|业务模块名
     * @return 上传后的路径集合
     * @throws IOException 获取文件流异常
     */
    @NonNull
    public static List<String> upload(@NonNull List<MultipartFile> files, @NonNull String endPoint,
                                      @NonNull String accessKeyId, @NonNull String accessKeySecret,
                                      @NonNull String bucketName, @NonNull String uploadPath, @NonNull String childrenPath) throws IOException {
        List<String> resultFilePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            resultFilePaths.add(upload(file, endPoint, accessKeyId, accessKeySecret, bucketName, uploadPath, childrenPath));
        }
        return resultFilePaths;
    }

    /**
     * 下载文件
     *
     * @param ossFilePath     OSS文件路径
     * @param request         HttpServletRequest
     * @param response        HttpServletResponse
     * @param endPoint        Endpoint
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @throws IOException 文件流转文件失败
     */
    public static void download(@NonNull String ossFilePath, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull String endPoint, @NonNull String accessKeyId, @NonNull String accessKeySecret, @NonNull String bucketName) throws IOException {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            ossFilePath = formatFilename(ossFilePath, true);
            OSSObject ossObject = ossClient.getObject(bucketName, ossFilePath);
            InputStream content = ossObject.getObjectContent();
            if (content != null) {
                MediaType mediaType;
                String fileBaseName = FilenameUtils.getName(ossFilePath);
                File file = new File(fileBaseName);
                FileUtils.copyInputStreamToFile(content, file);

                ServletContext servletContext = request.getServletContext();
                String mineType = servletContext.getMimeType(fileBaseName);
                try {
                    mediaType = MediaType.parseMediaType(mineType);
                } catch (Exception e) {
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                }

                response.setContentType(mediaType.getType());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=".concat(fileBaseName));
                response.setContentLengthLong(file.length());

                FileUtils.copyFile(file, response.getOutputStream());
            }
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param ossFilePath     OSS文件路径
     * @param endPoint        Endpoint
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     */
    public static void deleteObject(@NonNull String ossFilePath, @NonNull String endPoint,
                                    @NonNull String accessKeyId, @NonNull String accessKeySecret, @NonNull String bucketName) {
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
     * @param endPoint        Endpoint
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @return 删除结果
     * @see pro.haichuang.framework.sdk.aliyunoss.util.AliYunOssUtils#deleteObject(List, boolean, String, String, String, String)
     */
    public static List<String> deleteObject(@NonNull List<String> ossFilePaths, @NonNull String endPoint,
                                            @NonNull String accessKeyId, @NonNull String accessKeySecret, @NonNull String bucketName) {
        return deleteObject(ossFilePaths, false, endPoint, accessKeyId, accessKeySecret, bucketName);
    }

    /**
     * 批量删除文件
     *
     * @param ossFilePaths    OSS文件路径集合
     * @param quiet           返回模式 [default: false-详细模式, {true: 简单模式(删除失败的文件列表), false: 详细模式(删除成功的文件列表)}]
     * @param endPoint        Endpoint
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param bucketName      BucketName
     * @return 删除结果, 参考 {@code quiet} 字段
     */
    public static List<String> deleteObject(@NonNull List<String> ossFilePaths, boolean quiet, @NonNull String endPoint,
                                            @NonNull String accessKeyId, @NonNull String accessKeySecret, @NonNull String bucketName) {
        String uuid = UUIDUtils.Local.get();
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            ossFilePaths = ossFilePaths.stream().map(path -> formatFilename(path, true)).collect(Collectors.toList());
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(ossFilePaths);
            deleteObjectsRequest.setQuiet(quiet);
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
            LOGGER.info("[{}] 删除文件 [uuid: {}, bucketName: {}, resultPath: {}]", LOG_TAG, uuid, bucketName, ossFilePaths);
            return deleteObjectsResult.getDeletedObjects();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 拼接文件名
     *
     * @param file         文件
     * @param uploadPath   上传相对路径
     * @param childrenPath 上传子路径
     * @return 拼接后的文件名 {上传相对路径 + 上传子路径 + 新文件名}
     */
    @NonNull
    private static String concatFilename(@NonNull MultipartFile file, @NonNull String uploadPath, @NonNull String childrenPath) {
        String fileOriginalExtensionName = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileNewName = fileOriginalExtensionName != null && !fileOriginalExtensionName.isEmpty()
                ? UUIDUtils.random().concat(".") + fileOriginalExtensionName
                : UUIDUtils.random();
        return formatFilename(FilenameUtils.concat(FilenameUtils.concat(uploadPath, childrenPath), fileNewName), true);
    }

    /**
     * 拼接文件名
     *
     * @param filePath     文件路径
     * @param uploadPath   上传相对路径
     * @param childrenPath 上传子路径
     * @return 拼接后的文件名 {上传相对路径 + 上传子路径 + 新文件名}
     */
    @NonNull
    private static String concatFilename(@NonNull String filePath, @NonNull String uploadPath, @NonNull String childrenPath) {
        String fileExtensionName = FilenameUtils.getExtension(filePath);
        String fileNewName = !fileExtensionName.isEmpty()
                ? UUIDUtils.random().concat(".").concat(fileExtensionName)
                : UUIDUtils.random();
        return formatFilename(FilenameUtils.concat(FilenameUtils.concat(uploadPath, childrenPath), fileNewName), true);
    }

    /**
     * 格式化文件名
     *
     * @param filename 文件名
     * @return 格式化后的文件名
     */
    @NonNull
    private static String formatFilename(@NonNull String filename, boolean isReplaceFirstSeparator) {
        filename = filename.replaceAll("\\\\", "/").replaceAll("//", "/");
        return isReplaceFirstSeparator && (StringUtils.equals(String.valueOf(filename.charAt(0)), "/")) ? filename.substring(1) : filename;
    }
}
