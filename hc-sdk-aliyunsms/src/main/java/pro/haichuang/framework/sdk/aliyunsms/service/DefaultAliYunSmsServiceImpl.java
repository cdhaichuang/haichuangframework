package pro.haichuang.framework.sdk.aliyunsms.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.sdk.aliyunsms.config.properties.AliYunSmsProperties;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsConfigErrorEnum;
import pro.haichuang.framework.sdk.aliyunsms.exception.AliYunSmsConfigException;
import pro.haichuang.framework.sdk.aliyunsms.util.AliYunSmsUtils;

/**
 * AliYunSmsService默认实现
 *
 * <p>该类为 {@link AliYunSmsService} 默认实现
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
public class DefaultAliYunSmsServiceImpl implements AliYunSmsService {

    @Autowired
    private AliYunSmsProperties aliYunSmsProperties;

    @Override
    public boolean send(@Nullable String signName, @Nullable String templateCode,
                        String phoneNumbers, JSONObject templateParam) {
        validateProperties();
        if (signName == null || signName.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (templateCode == null || templateCode.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.TEMPLATE_CODE_NOT_CONFIGURED);
        }
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), signName, templateCode, phoneNumbers, templateParam);
    }

    @Override
    public boolean send(@Nullable String templateCode, String phoneNumbers, JSONObject templateParam) {
        validateProperties();
        String signName = aliYunSmsProperties.getSignName();
        if (signName == null || signName.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (templateCode == null || templateCode.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.TEMPLATE_CODE_NOT_CONFIGURED);
        }
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), aliYunSmsProperties.getSignName(),
                templateCode, phoneNumbers, templateParam);
    }

    @Override
    public boolean send(String phoneNumbers, JSONObject templateParam) {
        validateProperties();
        String signName = aliYunSmsProperties.getSignName();
        String defaultTemplateCode = aliYunSmsProperties.getDefaultTemplateCode();
        if (signName == null || signName.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (defaultTemplateCode == null || defaultTemplateCode.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.TEMPLATE_CODE_NOT_CONFIGURED);
        }
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), aliYunSmsProperties.getSignName(),
                aliYunSmsProperties.getDefaultTemplateCode(), phoneNumbers, templateParam);
    }

    /**
     * 验证配置文件
     *
     * @since 1.0.0.211014
     */
    private void validateProperties() {
        String accessKeyId = aliYunSmsProperties.getAccessKeyId();
        String accessKeySecret = aliYunSmsProperties.getAccessKeySecret();

        if (accessKeyId == null || accessKeyId.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.ACCESS_KEY_ID_NOT_CONFIGURED);
        }
        if (accessKeySecret == null || accessKeySecret.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.ACCESS_KEY_SECRET_NOT_CONFIGURED);
        }
    }
}
