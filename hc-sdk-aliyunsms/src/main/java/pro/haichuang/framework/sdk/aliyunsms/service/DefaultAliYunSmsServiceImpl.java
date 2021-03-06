package pro.haichuang.framework.sdk.aliyunsms.service;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.constant.PatternConstant;
import pro.haichuang.framework.sdk.aliyunsms.config.properties.AliYunSmsProperties;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsConfigErrorEnum;
import pro.haichuang.framework.sdk.aliyunsms.enums.error.AliYunSmsSendErrorEnum;
import pro.haichuang.framework.sdk.aliyunsms.exception.AliYunSmsConfigException;
import pro.haichuang.framework.sdk.aliyunsms.exception.AliYunSmsSendException;
import pro.haichuang.framework.sdk.aliyunsms.util.AliYunSmsUtils;

/**
 * AliYunSmsService默认实现
 *
 * <p>该类为 {@link AliYunSmsService} 默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultAliYunSmsServiceImpl implements AliYunSmsService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private AliYunSmsProperties aliYunSmsProperties;

    @Override
    public boolean send(String signName, String templateCode,
                        String phoneNumbers, @Nullable JSONObject templateParam)
            throws AliYunSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);
        if (signName.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (templateCode.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.TEMPLATE_CODE_NOT_CONFIGURED);
        }
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), signName, templateCode, phoneNumbers, templateParam);
    }

    @Override
    public boolean send(String templateCode, String phoneNumbers, @Nullable JSONObject templateParam)
            throws AliYunSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);
        String signName = aliYunSmsProperties.getDefaultSignName();
        if (signName == null || signName.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (templateCode.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.TEMPLATE_CODE_NOT_CONFIGURED);
        }
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), aliYunSmsProperties.getDefaultSignName(),
                templateCode, phoneNumbers, templateParam);
    }

    @Override
    public boolean send(String phoneNumbers, @Nullable JSONObject templateParam)
            throws AliYunSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);
        String signName = aliYunSmsProperties.getDefaultSignName();
        String defaultTemplateCode = aliYunSmsProperties.getDefaultTemplateCode();
        if (signName == null || signName.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (defaultTemplateCode == null || defaultTemplateCode.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.TEMPLATE_CODE_NOT_CONFIGURED);
        }
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), aliYunSmsProperties.getDefaultSignName(),
                aliYunSmsProperties.getDefaultTemplateCode(), phoneNumbers, templateParam);
    }

    /**
     * 验证配置文件
     *
     * @throws AliYunSmsConfigException 阿里云短信配置异常
     * @since 1.1.0.211021
     */
    private void validateProperties() throws AliYunSmsConfigException {
        String accessKeyId = aliYunSmsProperties.getAccessKeyId();
        String accessKeySecret = aliYunSmsProperties.getAccessKeySecret();

        if (accessKeyId == null || accessKeyId.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.ACCESS_KEY_ID_NOT_CONFIGURED);
        }
        if (accessKeySecret == null || accessKeySecret.isEmpty()) {
            throw new AliYunSmsConfigException(AliYunSmsConfigErrorEnum.ACCESS_KEY_SECRET_NOT_CONFIGURED);
        }
    }

    /**
     * 验证参数
     *
     * @param phoneNumbers 手机号, 多个逗号分割
     * @throws AliYunSmsSendException 阿里云短信发送异常
     * @since 1.1.0.211021
     */
    private void validateParams(String phoneNumbers) throws AliYunSmsSendException {
        //noinspection AlibabaUndefineMagicConstant
        for (String phoneNumber : phoneNumbers.split(",")) {
            if (!ReUtil.isMatch(PatternConstant.PHONE, phoneNumber.replaceFirst("\\+", ""))) {
                throw new AliYunSmsSendException(AliYunSmsSendErrorEnum.MALFORMED_PHONE_NUMBER);
            }
        }
    }
}
