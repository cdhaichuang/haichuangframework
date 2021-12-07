package pro.haichuang.framework.sdk.chuanglansms.service;

import cn.hutool.core.util.ReUtil;
import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.base.constant.PatternConstant;
import pro.haichuang.framework.sdk.chuanglansms.config.properties.ChuangLanSmsProperties;
import pro.haichuang.framework.sdk.chuanglansms.enums.error.ChuangLanSmsConfigErrorEnum;
import pro.haichuang.framework.sdk.chuanglansms.enums.error.ChuangLanSmsSendErrorEnum;
import pro.haichuang.framework.sdk.chuanglansms.exception.ChuangLanSmsConfigException;
import pro.haichuang.framework.sdk.chuanglansms.exception.ChuangLanSmsSendException;
import pro.haichuang.framework.sdk.chuanglansms.response.SendResponse;
import pro.haichuang.framework.sdk.chuanglansms.util.ChuangLanSmsUtils;

/**
 * ChuangLanSmsService默认实现
 *
 * <p>该类为 {@link ChuangLanSmsService} 默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultChuangLanSmsServiceImpl implements ChuangLanSmsService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private ChuangLanSmsProperties chuangLanSmsProperties;

    @Override
    public SendResponse send(String signName, String message, String phoneNumbers) throws ChuangLanSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);
        if (signName.isEmpty()) {
            throw new ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum.SIGN_NAME_NOT_CONFIGURED);
        }
        if (message.isEmpty()) {
            throw new ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum.SEND_MESSAGE_NOT_CONFIGURED);
        }
        return ChuangLanSmsUtils.send(chuangLanSmsProperties.getApiAccount(), chuangLanSmsProperties.getApiPassword(),
                chuangLanSmsProperties.getHost(), signName, message, phoneNumbers);
    }

    @Override
    public SendResponse send(String message, String phoneNumbers) throws ChuangLanSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);
        if (message.isEmpty()) {
            throw new ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum.SEND_MESSAGE_NOT_CONFIGURED);
        }
        return ChuangLanSmsUtils.send(chuangLanSmsProperties.getApiAccount(), chuangLanSmsProperties.getApiPassword(),
                chuangLanSmsProperties.getHost(), chuangLanSmsProperties.getDefaultSignName(), message, phoneNumbers);
    }

    @Override
    public SendResponse send(String phoneNumbers) throws ChuangLanSmsSendException {
        validateProperties();
        validateParams(phoneNumbers);
        return ChuangLanSmsUtils.send(chuangLanSmsProperties.getApiAccount(), chuangLanSmsProperties.getApiPassword(),
                chuangLanSmsProperties.getHost(), chuangLanSmsProperties.getDefaultSignName(),
                chuangLanSmsProperties.getDefaultSignName(), phoneNumbers);
    }

    /**
     * 验证配置文件
     *
     * @since 1.1.0.211021
     */
    private void validateProperties() throws ChuangLanSmsConfigException {
        String apiAccount = chuangLanSmsProperties.getApiAccount();
        String apiPassword = chuangLanSmsProperties.getApiPassword();
        String host = chuangLanSmsProperties.getHost();

        if (apiAccount == null || apiAccount.isEmpty()) {
            throw new ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum.API_ACCOUNT_NOT_CONFIGURED);
        }
        if (apiPassword == null || apiPassword.isEmpty()) {
            throw new ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum.API_PASSWORD_NOT_CONFIGURED);
        }
        if (host == null || host.isEmpty()) {
            throw new ChuangLanSmsConfigException(ChuangLanSmsConfigErrorEnum.HOST_NOT_CONFIGURED);
        }
    }

    /**
     * 验证参数
     *
     * @since 1.1.0.211021
     */
    private void validateParams(String phoneNumbers) throws ChuangLanSmsSendException {
        //noinspection AlibabaUndefineMagicConstant
        for (String phoneNumber : phoneNumbers.split(",")) {
            if (!ReUtil.isMatch(PatternConstant.PHONE, phoneNumber.replaceFirst("\\+", ""))) {
                throw new ChuangLanSmsSendException(ChuangLanSmsSendErrorEnum.MALFORMED_PHONE_NUMBER);
            }
        }
    }
}
