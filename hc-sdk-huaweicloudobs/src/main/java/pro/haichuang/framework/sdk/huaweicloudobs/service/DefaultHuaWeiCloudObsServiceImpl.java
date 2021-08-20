package pro.haichuang.framework.sdk.huaweicloudobs.service;

import com.obs.services.model.DeleteObjectsResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.enums.upload.UploadTypeEnum;
import pro.haichuang.framework.base.util.common.ValidateUtils;
import pro.haichuang.framework.sdk.huaweicloudobs.config.properties.HuaWeiCloudObsProperties;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeCloudObsConfigErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeCloudObsUploadErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudobs.util.HuaWeiCloudObsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * AliYunOssService默认实现
 *
 * <p>该类为 {@link HuaWeiCloudObsService} 默认实现
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultHuaWeiCloudObsServiceImpl implements HuaWeiCloudObsService {

    @Autowired
    private HuaWeiCloudObsProperties huaWeiCloudObsProperties;

    @SneakyThrows
    @Override
    public String uploadByMultipart(MultipartFile uploadFile, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFile,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByMultipart(MultipartFile uploadFile, String newFileName, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFile, newFileName,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByMultipart(List<MultipartFile> uploadFiles, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFiles,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles, LinkedList<String> newFileNames, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFiles, newFileNames,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByPath(String absoluteFilePath, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByPath(String absoluteFilePath, String newFileName, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePath, newFileName,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByPath(List<String> absoluteFilePaths, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePaths,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByPath(LinkedList<String> absoluteFilePaths, LinkedList<String> newFileNames, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePaths, newFileNames,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByFile(File absoluteFilePath, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public String uploadByFile(File absoluteFilePath, String newFileName, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePath, newFileName,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(List<File> absoluteFilePaths, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePaths,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(LinkedList<File> absoluteFilePaths, LinkedList<String> newFileNames, String pathOfBizName, UploadTypeEnum uploadTypeEnum) {
        validateProperties();
        validateParams(pathOfBizName, uploadTypeEnum);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePaths, newFileNames,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                pathOfBizName, uploadTypeEnum.value());
    }

    @SneakyThrows
    @Override
    public void downloadToResponse(String ossFilePath, HttpServletRequest request, HttpServletResponse response) {
        validateProperties();
        HuaWeiCloudObsUtils.downloadToResponse(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                request, response);
    }

    @SneakyThrows
    @Override
    public void downloadToResponse(String ossFilePath, String fileName,
                                   HttpServletRequest request, HttpServletResponse response) {
        validateProperties();
        HuaWeiCloudObsUtils.downloadToResponse(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                fileName, request, response);
    }

    @SneakyThrows
    @Override
    public File downloadToFile(String ossFilePath) {
        validateProperties();
        return HuaWeiCloudObsUtils.downloadToFile(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint());
    }

    @SneakyThrows
    @Override
    public File downloadToFile(String ossFilePath, String outFileName) {
        validateProperties();
        return HuaWeiCloudObsUtils.downloadToFile(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                outFileName);
    }

    @SneakyThrows
    @Override
    public File downloadToFile(String ossFilePath, File outFile) {
        validateProperties();
        return HuaWeiCloudObsUtils.downloadToFile(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                outFile);
    }

    @SneakyThrows
    @Override
    public void deleteObjectResSuccess(String ossFilePath) {
        validateProperties();
        HuaWeiCloudObsUtils.deleteObject(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint());
    }

    @SneakyThrows
    @Override
    public List<DeleteObjectsResult.DeleteObjectResult> deleteObjectResSuccess(Collection<String> ossFilePaths) {
        validateProperties();
        return HuaWeiCloudObsUtils.deleteObjectResSuccess(ossFilePaths,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint());
    }

    @SneakyThrows
    @Override
    public List<DeleteObjectsResult.ErrorResult> deleteObjectResError(Collection<String> ossFilePaths) {
        validateProperties();
        return HuaWeiCloudObsUtils.deleteObjectResError(ossFilePaths,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint());
    }

    /**
     * 验证配置文件
     */
    private void validateProperties() {
        String accessKeyId = huaWeiCloudObsProperties.getAccessKeyId();
        String accessKeySecret = huaWeiCloudObsProperties.getAccessKeySecret();
        String bucketName = huaWeiCloudObsProperties.getBucketName();
        String endpoint = huaWeiCloudObsProperties.getEndpoint();

        ValidateUtils.validate(accessKeyId == null || accessKeyId.isEmpty(),
                HuaWeCloudObsConfigErrorEnum.ACCESS_KEY_ID_NOT_CONFIGURED);
        ValidateUtils.validate(accessKeySecret == null || accessKeySecret.isEmpty(),
                HuaWeCloudObsConfigErrorEnum.ACCESS_KEY_SECRET_NOT_CONFIGURED);
        ValidateUtils.validate(bucketName == null || bucketName.isEmpty(),
                HuaWeCloudObsConfigErrorEnum.BUCKET_NAME_NOT_CONFIGURED);
        ValidateUtils.validate(endpoint == null || endpoint.isEmpty(),
                HuaWeCloudObsConfigErrorEnum.END_POINT_NOT_CONFIGURED);
    }

    /**
     * 验证参数
     *
     * @param pathOfBizName  上传主路径, 建议填写业务模块相关名称
     * @param uploadTypeEnum 上传子路径
     */
    private void validateParams(@Nullable String pathOfBizName, @Nullable UploadTypeEnum uploadTypeEnum) {
        ValidateUtils.validate(pathOfBizName == null || pathOfBizName.isEmpty(),
                HuaWeCloudObsUploadErrorEnum.UPLOAD_BASE_PATH_IS_NULL);
        ValidateUtils.validate(uploadTypeEnum == null,
                HuaWeCloudObsUploadErrorEnum.UPLOAD_SUB_PATH_IS_NULL);
    }
}
