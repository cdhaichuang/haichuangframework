package pro.haichuang.framework.sdk.aliyunsms.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.util.common.ValidateUtils;
import pro.haichuang.framework.sdk.aliyunsms.config.properties.AliYunSmsProperties;
import pro.haichuang.framework.sdk.aliyunsms.util.AliYunSmsUtils;

/**
 * AliYunSmsService默认实现
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public class DefaultAliYunSmsServiceImpl implements AliYunSmsService {

    @Autowired
    private AliYunSmsProperties aliYunSmsProperties;

    @Override
    public boolean send(@Nullable String signName, @Nullable String templateCode,
                        String phoneNumbers, JSONObject templateParam) {
        validateProperties(false, false);
        ValidateUtils.validate(signName == null || signName.isEmpty(),
                RequestParamErrorEnum.PARAMETER_EMPTY, "[短信签名] 不能为空");
        ValidateUtils.validate(templateCode == null || templateCode.isEmpty(),
                RequestParamErrorEnum.PARAMETER_EMPTY, "[短信模板ID] 不能为空");
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), signName, templateCode, phoneNumbers, templateParam);
    }

    @Override
    public boolean send(@Nullable String templateCode, String phoneNumbers, JSONObject templateParam) {
        validateProperties(true, false);
        ValidateUtils.validate(templateCode == null || templateCode.isEmpty(),
                RequestParamErrorEnum.PARAMETER_EMPTY, "[短信模板ID] 不能为空");
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), aliYunSmsProperties.getSignName(),
                templateCode, phoneNumbers, templateParam);
    }

    @Override
    public boolean send(String phoneNumbers, JSONObject templateParam) {
        validateProperties(true, true);
        return AliYunSmsUtils.send(aliYunSmsProperties.getAccessKeyId(),
                aliYunSmsProperties.getAccessKeySecret(), aliYunSmsProperties.getSignName(),
                aliYunSmsProperties.getTemplateCode(), phoneNumbers, templateParam);
    }

    /**
     * 验证配置文件
     *
     * @param isValidateSignName     是否验证短信签名
     * @param isValidateTemplateCode 是否验证短信模版ID
     */
    private void validateProperties(boolean isValidateSignName, boolean isValidateTemplateCode) {
        ValidateUtils.validate(aliYunSmsProperties.getRegionId() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[RegionId] 未在Yaml进行配置");
        ValidateUtils.validate(aliYunSmsProperties.getAccessKeyId() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[AccessKeyId] 未在Yaml进行配置");
        ValidateUtils.validate(aliYunSmsProperties.getAccessKeySecret() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[AccessKeySecret] 未在Yaml进行配置");
        ValidateUtils.validate(isValidateSignName && aliYunSmsProperties.getSignName() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[短信签名] 未在Yaml进行配置");
        ValidateUtils.validate(isValidateTemplateCode && aliYunSmsProperties.getTemplateCode() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[短信模板ID] 未在Yaml进行配置");
    }
}
