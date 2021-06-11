package pro.haichuang.framework.sdk.aliyunoss.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pro.haichuang.framework.base.enums.abnormal.client.RequestParamAbnormalEnum;
import pro.haichuang.framework.base.util.common.ValidateUtils;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;
import pro.haichuang.framework.sdk.aliyunoss.util.AliYunOssUtils;

import java.util.List;

/**
 * AliYunOssService默认实现
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class DefaultAliYunOssServiceImpl implements AliYunOssService {

    @Autowired
    private AliYunOssProperties aliYunOssProperties;

    @SneakyThrows
    @Override
    public String upload(MultipartFile file, String uploadPath, String childrenPath) {
        validateProperties();
        validateParams(uploadPath, childrenPath);
        return AliYunOssUtils.upload(file, aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(),
                aliYunOssProperties.getAccessKeySecret(), aliYunOssProperties.getBucketName(),
                uploadPath, childrenPath);
    }

    @SneakyThrows
    @Override
    public List<String> upload(List<MultipartFile> files, String uploadPath, String childrenPath) {
        validateProperties();
        validateParams(uploadPath, childrenPath);
        return AliYunOssUtils.upload(files, aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(),
                aliYunOssProperties.getAccessKeySecret(), aliYunOssProperties.getBucketName(),
                uploadPath, childrenPath);
    }

    @Override
    public void deleteObject(String ossFilePath) {
        validateProperties();
        AliYunOssUtils.deleteObject(ossFilePath, aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(),
                aliYunOssProperties.getAccessKeySecret(), aliYunOssProperties.getBucketName());
    }

    @Override
    public List<String> deleteObject(List<String> ossFilePaths) {
        validateProperties();
        return AliYunOssUtils.deleteObject(ossFilePaths, aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(),
                aliYunOssProperties.getAccessKeySecret(), aliYunOssProperties.getBucketName());
    }

    @Override
    public List<String> deleteObject(List<String> ossFilePaths, boolean quiet) {
        validateProperties();
        return AliYunOssUtils.deleteObject(ossFilePaths, quiet, aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(),
                aliYunOssProperties.getAccessKeySecret(), aliYunOssProperties.getBucketName());
    }

    /**
     * 验证配置文件
     */
    private void validateProperties() {
        ValidateUtils.validate(aliYunOssProperties.getEndpoint() == null, RequestParamAbnormalEnum.PARAMETER_EMPTY, "[EndPoint] 未在Yaml进行配置");
        ValidateUtils.validate(aliYunOssProperties.getAccessKeyId() == null, RequestParamAbnormalEnum.PARAMETER_EMPTY, "[AccessKeyId] 未在Yaml进行配置");
        ValidateUtils.validate(aliYunOssProperties.getAccessKeySecret() == null, RequestParamAbnormalEnum.PARAMETER_EMPTY, "[AccessKeySecret] 未在Yaml进行配置");
        ValidateUtils.validate(aliYunOssProperties.getBucketName() == null, RequestParamAbnormalEnum.PARAMETER_EMPTY, "[BucketName] 未在Yaml进行配置");
    }

    /**
     * 验证参数
     *
     * @param uploadPath   上传相对路径
     * @param childrenPath 上传子路径|业务模块名
     */
    private void validateParams(@Nullable String uploadPath, @Nullable String childrenPath) {
        ValidateUtils.validate(uploadPath == null || uploadPath.isEmpty(),
                RequestParamAbnormalEnum.PARAMETER_EMPTY, "[上传相对路径] 不能为空");
        ValidateUtils.validate(childrenPath == null || childrenPath.isEmpty(),
                RequestParamAbnormalEnum.PARAMETER_EMPTY, "[上传子路径|业务模块名] 不能为空");
    }
}
