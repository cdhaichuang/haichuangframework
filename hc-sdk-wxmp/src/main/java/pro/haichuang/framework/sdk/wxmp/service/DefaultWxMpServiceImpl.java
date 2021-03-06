package pro.haichuang.framework.sdk.wxmp.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.base.enums.error.client.AuthorityErrorEnum;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.util.common.ResponseUtils;
import pro.haichuang.framework.sdk.wxmp.config.properties.WxMpProperties;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpBaseAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpJsApiTicketDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpUserInfoDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.enums.error.WxMpConfigErrorEnum;
import pro.haichuang.framework.sdk.wxmp.exception.WxMpConfigException;
import pro.haichuang.framework.sdk.wxmp.exception.WxMpExecuteException;
import pro.haichuang.framework.sdk.wxmp.key.WxMpKey;
import pro.haichuang.framework.sdk.wxmp.store.WxMpDataStore;
import pro.haichuang.framework.sdk.wxmp.util.WxMpUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * WxMpService默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultWxMpServiceImpl implements WxMpService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private WxMpProperties wxMpProperties;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private WxMpDataStore wxMpDataStore;

    @SneakyThrows
    @Override
    public void verifyWxMessage(String signature, String timestamp, String nonce,
                                String echoStr, HttpServletResponse response) {
        validateProperties();
        String token = wxMpProperties.getToken();
        if (token == null || token.isEmpty()) {
            throw new WxMpConfigException(WxMpConfigErrorEnum.Token_NOT_CONFIGURED);
        }
        if (WxMpUtils.checkSignature(wxMpProperties.getToken(), signature, timestamp, nonce)) {
            ResponseUtils.writeOfOriginal(response, echoStr);
        } else {
            ResponseUtils.writeOfJson(response, ResultVO.other(AuthorityErrorEnum.ACCESS_BLOCKED));
        }
    }

    @Override
    public String getBaseAccessToken() throws WxMpExecuteException {
        validateProperties();
        String baseAccessToken = wxMpDataStore.getBaseAccessToken(WxMpKey.baseAccessToken());
        // 当缓存中 [WxMpBaseAccessTokenDTO] 为空时则向微信发起请求获取 [WxMpBaseAccessTokenDTO] 并存入缓存中
        if (baseAccessToken == null || baseAccessToken.isEmpty()) {
            WxMpBaseAccessTokenDTO wxMpBaseAccessTokenDTO = WxMpUtils.getBaseAccessToken(
                    wxMpProperties.getAppId(), wxMpProperties.getAppSecret());
            baseAccessToken = wxMpBaseAccessTokenDTO.getAccessToken();
            wxMpDataStore.setBaseAccessToken(WxMpKey.baseAccessToken(),
                    wxMpBaseAccessTokenDTO.getAccessToken(), wxMpBaseAccessTokenDTO.getAccessTokenExpireTime());
        }
        return baseAccessToken;
    }

    @Override
    public WxMpWebAccessTokenDTO getWebAccessTokenByOpenId(String openId) throws WxMpExecuteException {
        validateProperties();
        WxMpWebAccessTokenDTO wxMpWebAccessTokenDTO = null;
        String webAccessToken = wxMpDataStore.getWebAccessToken(WxMpKey.webAccessToken(openId));
        String webRefreshAccessToken = wxMpDataStore.getWebRefreshAccessToken(WxMpKey.webRefreshAccessToken(openId));
        // 当 [WebAccessToken] 不为空时则设置结果为缓存中的数据
        if (webAccessToken != null && !webAccessToken.isEmpty()) {
            wxMpWebAccessTokenDTO = new WxMpWebAccessTokenDTO();
            wxMpWebAccessTokenDTO.setWebAccessToken(webAccessToken).setWebRefreshToken(webRefreshAccessToken).setOpenId(openId);
        }
        // 当 [WebRefreshAccessToken] 存在时则刷新 [WebAccessToken] 与 [WebRefreshAccessToken]
        if (webRefreshAccessToken != null && !webRefreshAccessToken.isEmpty()) {
            wxMpWebAccessTokenDTO = WxMpUtils.refreshWebAccessToken(wxMpProperties.getAppId(), webRefreshAccessToken);
            wxMpDataStore.setWebAccessToken(WxMpKey.webAccessToken(openId),
                    wxMpWebAccessTokenDTO.getWebAccessToken(), wxMpWebAccessTokenDTO.getWebAccessTokenExpireTime());
            wxMpDataStore.setWebRefreshAccessToken(WxMpKey.webRefreshAccessToken(openId),
                    wxMpWebAccessTokenDTO.getWebRefreshToken(), wxMpWebAccessTokenDTO.getWebRefreshTokenExpireTime());
        }
        return wxMpWebAccessTokenDTO;
    }

    @Override
    public WxMpWebAccessTokenDTO getWebAccessTokenByCode(String code) throws WxMpExecuteException {
        validateProperties();
        WxMpWebAccessTokenDTO wxMpWebAccessTokenDTO = WxMpUtils.getWebAccessToken(wxMpProperties.getAppId(),
                wxMpProperties.getAppSecret(), code);
        wxMpDataStore.setWebAccessToken(WxMpKey.webAccessToken(wxMpWebAccessTokenDTO.getOpenId()),
                wxMpWebAccessTokenDTO.getWebAccessToken(), wxMpWebAccessTokenDTO.getWebAccessTokenExpireTime());
        wxMpDataStore.setWebRefreshAccessToken(WxMpKey.webRefreshAccessToken(wxMpWebAccessTokenDTO.getOpenId()),
                wxMpWebAccessTokenDTO.getWebRefreshToken(), wxMpWebAccessTokenDTO.getWebRefreshTokenExpireTime());
        return wxMpWebAccessTokenDTO;
    }

    @Override
    public String getJsApiTicket() throws WxMpExecuteException {
        validateProperties();
        String wxMpJsApiTicket = wxMpDataStore.getJsApiTicket(WxMpKey.jsApiTicket());
        if (wxMpJsApiTicket == null || !wxMpJsApiTicket.isEmpty()) {
            WxMpJsApiTicketDTO wxMpJsApiTicketDTO = WxMpUtils.getJsApiTicket(getBaseAccessToken());
            wxMpJsApiTicket = wxMpJsApiTicketDTO.getTicket();
            wxMpDataStore.setJsApiTicket(WxMpKey.jsApiTicket(), wxMpJsApiTicket,
                    wxMpJsApiTicketDTO.getEffectiveTime());
        }
        return wxMpJsApiTicket;
    }

    @Override
    public WxMpUserInfoDTO getUserInfo(String openId) throws WxMpExecuteException {
        return WxMpUtils.getUserInfo(getBaseAccessToken(), openId);
    }

    /**
     * 验证配置文件
     *
     * @since 1.1.0.211021
     */
    private void validateProperties() {
        String appId = wxMpProperties.getAppId();
        String appSecret = wxMpProperties.getAppSecret();

        if (appId == null) {
            throw new WxMpConfigException(WxMpConfigErrorEnum.ACCESS_ID_NOT_CONFIGURED);
        }
        if (appSecret == null) {
            throw new WxMpConfigException(WxMpConfigErrorEnum.ACCESS_ID_NOT_CONFIGURED);
        }
    }
}
