package pro.haichuang.framework.sdk.aliyunoss.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.util.common.ValidateUtils;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssConfigErrorEnum;
import pro.haichuang.framework.sdk.aliyunoss.enums.error.AliYunOssUploadErrorEnum;
import pro.haichuang.framework.base.enums.upload.UploadTypeEnum;
import pro.haichuang.framework.sdk.aliyunoss.util.AliYunOssUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * AliYunOssService默认实现
 *
 * <p>该类为 {@link AliYunOssService} 默认实现
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultAliYunOssServiceImpl implements AliYunOssService {

    @Autowired
    private AliYunOssProperties aliYunOssProperties;

    @SneakyThrows
    @Override
    public String uploadByMultipart(MultipartFile uploadFile, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByMultipart(uploadFile,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByMultipart(MultipartFile uploadFile, String newFileName, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByMultipart(uploadFile, newFileName,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByMultipart(List<MultipartFile> uploadFiles, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByMultipart(uploadFiles,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles, LinkedList<String> newFileNames, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByMultipart(uploadFiles, newFileNames,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByPath(String absoluteFilePath, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByPath(absoluteFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByPath(String absoluteFilePath, String newFileName, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByPath(absoluteFilePath, newFileName,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByPath(List<String> absoluteFilePaths, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByPath(absoluteFilePaths,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByPath(LinkedList<String> absoluteFilePaths, LinkedList<String> newFileNames, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByPath(absoluteFilePaths, newFileNames,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByFile(File absoluteFilePath, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByFile(absoluteFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @Override
    public String uploadByFile(File absoluteFilePath, String newFileName, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByFile(absoluteFilePath, newFileName,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(List<File> absoluteFilePaths, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByFile(absoluteFilePaths,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(LinkedList<File> absoluteFilePaths, LinkedList<String> newFileNames, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return AliYunOssUtils.uploadByFile(absoluteFilePaths, newFileNames,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public void downloadToResponse(String ossFilePath, HttpServletRequest request, HttpServletResponse response) {
        validateProperties();
        AliYunOssUtils.downloadToResponse(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                request, response);
    }

    @SneakyThrows
    @Override
    public void downloadToResponse(String ossFilePath, String fileName,
                                   HttpServletRequest request, HttpServletResponse response) {
        validateProperties();
        AliYunOssUtils.downloadToResponse(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                fileName, request, response);
    }

    @Override
    public File downloadToFile(String ossFilePath) {
        validateProperties();
        return AliYunOssUtils.downloadToFile(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    @Override
    public File downloadToFile(String ossFilePath, String fileName) {
        validateProperties();
        return AliYunOssUtils.downloadToFile(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                fileName);
    }

    @Override
    public File downloadToFile(String ossFilePath, File file) {
        validateProperties();
        return AliYunOssUtils.downloadToFile(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint(),
                file);
    }

    @Override
    public void deleteObject(String ossFilePath) {
        validateProperties();
        AliYunOssUtils.deleteObject(ossFilePath,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    @Override
    public List<String> deleteObject(Collection<String> ossFilePaths) {
        validateProperties();
        return AliYunOssUtils.deleteObject(ossFilePaths,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    @Override
    public List<String> deleteObject(Collection<String> ossFilePaths, boolean quiet) {
        validateProperties();
        return AliYunOssUtils.deleteObject(ossFilePaths, quiet,
                aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret(),
                aliYunOssProperties.getBucketName(), aliYunOssProperties.getEndpoint());
    }

    /**
     * 验证配置文件
     */
    private void validateProperties() {
        String accessKeyId = aliYunOssProperties.getAccessKeyId();
        String accessKeySecret = aliYunOssProperties.getAccessKeySecret();
        String bucketName = aliYunOssProperties.getBucketName();
        String endpoint = aliYunOssProperties.getEndpoint();

        ValidateUtils.validate(accessKeyId == null || accessKeyId.isEmpty(),
                AliYunOssConfigErrorEnum.ACCESS_KEY_ID_NOT_CONFIGURED);
        ValidateUtils.validate(accessKeySecret == null || accessKeySecret.isEmpty(),
                AliYunOssConfigErrorEnum.ACCESS_KEY_SECRET_NOT_CONFIGURED);
        ValidateUtils.validate(bucketName == null || bucketName.isEmpty(),
                AliYunOssConfigErrorEnum.BUCKET_NAME_NOT_CONFIGURED);
        ValidateUtils.validate(endpoint == null || endpoint.isEmpty(),
                AliYunOssConfigErrorEnum.END_POINT_NOT_CONFIGURED);
    }

    /**
     * 验证参数
     *
     * @param pathOfBizName  上传主路径, 建议填写业务模块相关名称
     * @param uploadTypeEnum 上传子路径
     */
    private void validateParams(@Nullable String pathOfBizName, @Nullable UploadTypeEnum uploadTypeEnum) {
        ValidateUtils.validate(pathOfBizName == null || pathOfBizName.isEmpty(),
                AliYunOssUploadErrorEnum.UPLOAD_BASE_PATH_IS_NULL);
        ValidateUtils.validate(uploadTypeEnum == null,
                AliYunOssUploadErrorEnum.UPLOAD_SUB_PATH_IS_NULL);
    }
}
