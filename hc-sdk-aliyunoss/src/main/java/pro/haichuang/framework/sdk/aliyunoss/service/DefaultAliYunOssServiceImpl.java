package pro.haichuang.framework.sdk.aliyunoss.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.enums.upload.UploadTypeEnum;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssConfigErrorEnum;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssUploadErrorEnum;
import pro.haichuang.framework.sdk.aliyunoss.exception.AliYunOssConfigException;
import pro.haichuang.framework.sdk.aliyunoss.exception.AliYunOssUploadException;
import pro.haichuang.framework.sdk.aliyunoss.util.AliYunOssUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * AliYunOssService默认实现
 *
 * <p>该类为 {@link AliYunOssService} 默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultAliYunOssServiceImpl implements AliYunOssService {

    private static final String[] DEFAULT_PATH_OF_BIZ_NAME = {"temp"};

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private AliYunOssProperties aliYunOssProperties;

    @Override
    public String uploadByMultipart(MultipartFile uploadFile,
                                    UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException, IOException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByMultipart(uploadFile,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public String uploadByMultipart(MultipartFile uploadFile, String newFileName,
                                    UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException, IOException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByMultipart(uploadFile, newFileName,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public List<String> uploadByMultipart(List<MultipartFile> uploadFiles,
                                          UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException, IOException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByMultipart(uploadFiles,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles,
                                          LinkedList<String> newFileNames, UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException, IOException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByMultipart(uploadFiles, newFileNames,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public String uploadByPath(String absoluteFilePath,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByPath(absoluteFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public String uploadByPath(String absoluteFilePath, String newFileName,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByPath(absoluteFilePath, newFileName,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public List<String> uploadByPath(List<String> absoluteFilePaths,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByPath(absoluteFilePaths,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public List<String> uploadByPath(LinkedList<String> absoluteFilePaths, LinkedList<String> newFileNames,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByPath(absoluteFilePaths, newFileNames,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public String uploadByFile(File absoluteFilePath,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByFile(absoluteFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public String uploadByFile(File absoluteFilePath, String newFileName,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName)
            throws AliYunOssUploadException {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByFile(absoluteFilePath, newFileName,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(List<File> absoluteFilePaths,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByFile(absoluteFilePaths,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(LinkedList<File> absoluteFilePaths, LinkedList<String> newFileNames,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum);
        pathOfBizName = pathOfBizName.length == 0 ? DEFAULT_PATH_OF_BIZ_NAME : pathOfBizName;
        return AliYunOssUtils.uploadByFile(absoluteFilePaths, newFileNames,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @Override
    public void downloadToResponse(String ossFilePath, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        validateProperties();
        AliYunOssUtils.downloadToResponse(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                request, response);
    }

    @Override
    public void downloadToResponse(String ossFilePath, @Nullable String fileName,
                                   HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        validateProperties();
        AliYunOssUtils.downloadToResponse(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                fileName, request, response);
    }

    @Override
    public File downloadToFile(String ossFilePath) throws AliYunOssConfigException {
        validateProperties();
        return AliYunOssUtils.downloadToFile(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    @Override
    public File downloadToFile(String ossFilePath, @Nullable String fileName) throws AliYunOssConfigException {
        validateProperties();
        return AliYunOssUtils.downloadToFile(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                fileName);
    }

    @Override
    public File downloadToFile(String ossFilePath, @Nullable File file) throws AliYunOssConfigException {
        validateProperties();
        return AliYunOssUtils.downloadToFile(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                file);
    }

    @Override
    public void deleteObject(String ossFilePath) throws AliYunOssConfigException {
        validateProperties();
        AliYunOssUtils.deleteObject(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    @Override
    public List<String> deleteObject(Collection<String> ossFilePaths) throws AliYunOssConfigException {
        validateProperties();
        return AliYunOssUtils.deleteObject(ossFilePaths,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    @Override
    public List<String> deleteObject(Collection<String> ossFilePaths, boolean quiet) throws AliYunOssConfigException {
        validateProperties();
        return AliYunOssUtils.deleteObject(ossFilePaths, quiet,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    /**
     * 验证配置文件
     *
     * @throws AliYunOssConfigException 配置异常
     * @since 1.1.0.211021
     */
    private void validateProperties() throws AliYunOssConfigException {
        String accessKeyId = aliYunOssProperties.getAccessKeyId();
        String accessKeySecret = aliYunOssProperties.getAccessKeySecret();
        String bucketName = aliYunOssProperties.getBucketName();
        String endpoint = aliYunOssProperties.getEndpoint();

        if (accessKeyId == null || accessKeyId.isEmpty()) {
            throw new AliYunOssConfigException(AliYunOssConfigErrorEnum.ACCESS_KEY_ID_NOT_CONFIGURED);
        }
        if (accessKeySecret == null || accessKeySecret.isEmpty()) {
            throw new AliYunOssConfigException(AliYunOssConfigErrorEnum.ACCESS_KEY_SECRET_NOT_CONFIGURED);
        }
        if (bucketName == null || bucketName.isEmpty()) {
            throw new AliYunOssConfigException(AliYunOssConfigErrorEnum.BUCKET_NAME_NOT_CONFIGURED);
        }
        if (endpoint == null || endpoint.isEmpty()) {
            throw new AliYunOssConfigException(AliYunOssConfigErrorEnum.END_POINT_NOT_CONFIGURED);
        }
    }

    /**
     * 验证参数
     *
     * @param uploadTypeEnum 文件类型
     * @throws AliYunOssUploadException 上传异常
     * @since 1.1.0.211021
     */
    private void validateParams(@Nullable UploadTypeEnum uploadTypeEnum) throws AliYunOssUploadException {
        if (uploadTypeEnum == null) {
            throw new AliYunOssUploadException(AliYunOssUploadErrorEnum.UPLOAD_FILE_TYPE_IS_NULL);
        }
    }
}
