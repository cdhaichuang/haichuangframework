package pro.haichuang.framework.sdk.huaweicloudobs.service;

import com.obs.services.model.DeleteObjectsResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.enums.upload.UploadTypeEnum;
import pro.haichuang.framework.sdk.huaweicloudobs.config.properties.HuaWeiCloudObsProperties;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeiCloudObsConfigErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudobs.enums.error.HuaWeiCloudObsUploadErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudobs.exception.HuaWeiCloudObsConfigException;
import pro.haichuang.framework.sdk.huaweicloudobs.exception.HuaWeiCloudObsUploadException;
import pro.haichuang.framework.sdk.huaweicloudobs.util.HuaWeiCloudObsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * HuaWeiCloudObsService默认实现
 *
 * <p>该类为 {@link HuaWeiCloudObsService} 默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultHuaWeiCloudObsServiceImpl implements HuaWeiCloudObsService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private HuaWeiCloudObsProperties huaWeiCloudObsProperties;

    @SneakyThrows
    @Override
    public String uploadByMultipart(MultipartFile uploadFile,
                                    UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFile,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public String uploadByMultipart(MultipartFile uploadFile, String newFileName,
                                    UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFile, newFileName,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByMultipart(List<MultipartFile> uploadFiles,
                                          UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFiles,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByMultipart(LinkedList<MultipartFile> uploadFiles, LinkedList<String> newFileNames,
                                          UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByMultipart(uploadFiles, newFileNames,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public String uploadByPath(String absoluteFilePath,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public String uploadByPath(String absoluteFilePath, String newFileName,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePath, newFileName,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByPath(List<String> absoluteFilePaths,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePaths,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByPath(LinkedList<String> absoluteFilePaths, LinkedList<String> newFileNames,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByPath(absoluteFilePaths, newFileNames,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public String uploadByFile(File absoluteFilePath,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public String uploadByFile(File absoluteFilePath, String newFileName,
                               UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePath, newFileName,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(List<File> absoluteFilePaths,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePaths,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public List<String> uploadByFile(LinkedList<File> absoluteFilePaths, LinkedList<String> newFileNames,
                                     UploadTypeEnum uploadTypeEnum, String... pathOfBizName) {
        validateProperties();
        validateParams(uploadTypeEnum, pathOfBizName);
        return HuaWeiCloudObsUtils.uploadByFile(absoluteFilePaths, newFileNames,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                uploadTypeEnum.value(), pathOfBizName);
    }

    @SneakyThrows
    @Override
    public void downloadToResponse(String ossFilePath,
                                   HttpServletRequest request, HttpServletResponse response) {
        validateProperties();
        HuaWeiCloudObsUtils.downloadToResponse(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                request, response);
    }

    @SneakyThrows
    @Override
    public void downloadToResponse(String ossFilePath, @Nullable String fileName,
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
    public File downloadToFile(String ossFilePath, @Nullable String outFileName) {
        validateProperties();
        return HuaWeiCloudObsUtils.downloadToFile(ossFilePath,
                huaWeiCloudObsProperties.getAccessKeyId(), huaWeiCloudObsProperties.getAccessKeySecret(),
                huaWeiCloudObsProperties.getBucketName(), huaWeiCloudObsProperties.getEndpoint(),
                outFileName);
    }

    @SneakyThrows
    @Override
    public File downloadToFile(String ossFilePath, @Nullable File outFile) {
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
     *
     * @since 1.1.0.211021
     */
    private void validateProperties() {
        String accessKeyId = huaWeiCloudObsProperties.getAccessKeyId();
        String accessKeySecret = huaWeiCloudObsProperties.getAccessKeySecret();
        String bucketName = huaWeiCloudObsProperties.getBucketName();
        String endpoint = huaWeiCloudObsProperties.getEndpoint();

        if (accessKeyId == null || accessKeyId.isEmpty()) {
            throw new HuaWeiCloudObsConfigException(HuaWeiCloudObsConfigErrorEnum.ACCESS_KEY_ID_NOT_CONFIGURED);
        }
        if (accessKeySecret == null || accessKeySecret.isEmpty()) {
            throw new HuaWeiCloudObsConfigException(HuaWeiCloudObsConfigErrorEnum.ACCESS_KEY_SECRET_NOT_CONFIGURED);
        }
        if (bucketName == null || bucketName.isEmpty()) {
            throw new HuaWeiCloudObsConfigException(HuaWeiCloudObsConfigErrorEnum.BUCKET_NAME_NOT_CONFIGURED);
        }
        if (endpoint == null || endpoint.isEmpty()) {
            throw new HuaWeiCloudObsConfigException(HuaWeiCloudObsConfigErrorEnum.END_POINT_NOT_CONFIGURED);
        }
    }

    /**
     * 验证参数
     *
     * @param uploadTypeEnum 上传子路径
     * @param pathOfBizName  上传主路径, 建议填写业务模块相关名称
     * @since 1.1.0.211021
     */
    private void validateParams(@Nullable UploadTypeEnum uploadTypeEnum, @Nullable String... pathOfBizName) {
        if (uploadTypeEnum == null) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.UPLOAD_FILE_TYPE_IS_NULL);
        }
        if (pathOfBizName == null || pathOfBizName.length == 0) {
            throw new HuaWeiCloudObsUploadException(HuaWeiCloudObsUploadErrorEnum.UPLOAD_PATH_IS_NULL);
        }
    }
}
