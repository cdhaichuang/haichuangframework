package pro.haichuang.framework.sdk.huaweicloudsms.service;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.constant.PatternConstant;
import pro.haichuang.framework.sdk.huaweicloudsms.config.properties.HuaWeiCloudSmsProperties;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.error.HuaWeiCloudSmsConfigErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudsms.enums.error.HuaWeiCloudSmsSendErrorEnum;
import pro.haichuang.framework.sdk.huaweicloudsms.exception.HuaWeiCloudSmsConfigException;
import pro.haichuang.framework.sdk.huaweicloudsms.exception.HuaWeiCloudSmsSendException;
import pro.haichuang.framework.sdk.huaweicloudsms.response.SendResponse;
import pro.haichuang.framework.sdk.huaweicloudsms.util.HuaWeiCloudSmsUtils;

import java.util.List;

/**
 * HuaWeiCloudSmsService默认实现
 *
 * <p>该类为 {@link HuaWeiCloudSmsService} 默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultHuaWeiCloudSmsServiceImpl implements HuaWeiCloudSmsService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private HuaWeiCloudSmsProperties huaWeiCloudSmsProperties;

    @Override
    public List<SendResponse.Result> send(String signName, String channelNumber, String templateId,
                                          String phoneNumbers, @Nullable JSONArray templateParams)
            throws HuaWeiCloudSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);

        if (signName.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (channelNumber.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.CHANNEL_NUMBER_NOT_CONFIGURED);
        }
        if (templateId.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.TEMPLATE_ID_NOT_CONFIGURED);
        }

        return HuaWeiCloudSmsUtils.send(huaWeiCloudSmsProperties.getAppUrl(),
                huaWeiCloudSmsProperties.getAppKey(),
                huaWeiCloudSmsProperties.getAppSecret(),
                signName, channelNumber, phoneNumbers, templateId, templateParams);
    }

    @Override
    public List<SendResponse.Result> send(String templateId,
                                          String phoneNumbers, @Nullable JSONArray templateParams)
            throws HuaWeiCloudSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);

        if (templateId.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.TEMPLATE_ID_NOT_CONFIGURED);
        }

        return HuaWeiCloudSmsUtils.send(huaWeiCloudSmsProperties.getAppUrl(),
                huaWeiCloudSmsProperties.getAppKey(),
                huaWeiCloudSmsProperties.getAppSecret(),
                huaWeiCloudSmsProperties.getDefaultSignName(),
                huaWeiCloudSmsProperties.getDefaultChannelNumber(),
                phoneNumbers, templateId, templateParams);
    }

    @Override
    public List<SendResponse.Result> send(String phoneNumbers, @Nullable JSONArray templateParams)
            throws HuaWeiCloudSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);

        return HuaWeiCloudSmsUtils.send(huaWeiCloudSmsProperties.getAppUrl(),
                huaWeiCloudSmsProperties.getAppKey(),
                huaWeiCloudSmsProperties.getAppSecret(),
                huaWeiCloudSmsProperties.getDefaultSignName(),
                huaWeiCloudSmsProperties.getDefaultChannelNumber(),
                phoneNumbers, huaWeiCloudSmsProperties.getDefaultTemplateId(), templateParams);
    }

    /**
     * 验证配置文件
     *
     * @since 1.1.0.211021
     */
    private void validateProperties() {
        String appKey = huaWeiCloudSmsProperties.getAppKey();
        String appSecret = huaWeiCloudSmsProperties.getAppSecret();
        String appUrl = huaWeiCloudSmsProperties.getAppUrl();

        if (appKey == null || appKey.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.APP_KEY_NOT_CONFIGURED);
        }
        if (appSecret == null || appSecret.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.APP_SECRET_NOT_CONFIGURED);
        }
        if (appUrl == null || appUrl.isEmpty()) {
            throw new HuaWeiCloudSmsConfigException(HuaWeiCloudSmsConfigErrorEnum.APP_URL_NOT_CONFIGURED);
        }
    }

    /**
     * 验证参数
     *
     * @since 1.1.0.211021
     * @throws HuaWeiCloudSmsSendException 华为云短信发送异常
     */
    private void validateParams(String phoneNumbers) throws HuaWeiCloudSmsSendException {
        //noinspection AlibabaUndefineMagicConstant
        for (String phoneNumber : phoneNumbers.split(",")) {
            if (!ReUtil.isMatch(PatternConstant.PHONE, phoneNumber.replaceFirst("\\+", ""))) {
                throw new HuaWeiCloudSmsSendException(HuaWeiCloudSmsSendErrorEnum.MALFORMED_PHONE_NUMBER);
            }
        }
    }
}
