package pro.haichuang.framework.sdk.wxmp.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.base.enums.error.client.AuthorityErrorEnum;
import pro.haichuang.framework.base.enums.error.client.RequestParamErrorEnum;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.util.common.ResponseUtils;
import pro.haichuang.framework.base.util.common.ValidateUtils;
import pro.haichuang.framework.sdk.wxmp.component.WxMpKeyComponent;
import pro.haichuang.framework.sdk.wxmp.config.properties.WxMpProperties;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpBaseAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpJsApiTicketDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpUserInfoDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.store.WxMpDataStore;
import pro.haichuang.framework.sdk.wxmp.util.WxMpUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * WxMpService默认实现
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultWxMpServiceImpl implements WxMpService {

    @Autowired
    private WxMpProperties wxMpProperties;
    @Autowired
    private WxMpDataStore wxMpDataStore;

    @SneakyThrows
    @Override
    public void verifyWxMessage(String signature, String timestamp, String nonce,
                                String echoStr, HttpServletResponse response) {
        validateProperties();
        ValidateUtils.validate(wxMpProperties.getToken() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[Token] 未在Yaml进行配置");
        if (WxMpUtils.checkSignature(wxMpProperties.getToken(), signature, timestamp, nonce)) {
            ResponseUtils.writeOfOriginal(response, echoStr);
        } else {
            ResponseUtils.writeOfJson(response, ResultVO.other(AuthorityErrorEnum.ACCESS_BLOCKED));
        }
    }

    @Override
    public String getBaseAccessToken() {
        validateProperties();
        String baseAccessToken = wxMpDataStore.getBaseAccessToken(WxMpKeyComponent.baseAccessToken());
        // 当缓存中 [WxMpBaseAccessTokenDTO] 为空时则向微信发起请求获取 [WxMpBaseAccessTokenDTO] 并存入缓存中
        if (baseAccessToken == null || baseAccessToken.isEmpty()) {
            WxMpBaseAccessTokenDTO wxMpBaseAccessTokenDTO = WxMpUtils.getBaseAccessToken(
                    wxMpProperties.getAppId(), wxMpProperties.getAppSecret());
            baseAccessToken = wxMpBaseAccessTokenDTO.getAccessToken();
            wxMpDataStore.setBaseAccessToken(WxMpKeyComponent.baseAccessToken(),
                    wxMpBaseAccessTokenDTO.getAccessToken(), wxMpBaseAccessTokenDTO.getAccessTokenExpireTime());
        }
        return baseAccessToken;
    }

    @Override
    public WxMpWebAccessTokenDTO getWebAccessTokenByOpenId(String openId) {
        validateProperties();
        WxMpWebAccessTokenDTO wxMpWebAccessTokenDTO = null;
        String webAccessToken = wxMpDataStore.getWebAccessToken(WxMpKeyComponent.webAccessToken(openId));
        String webRefreshAccessToken = wxMpDataStore.getWebRefreshAccessToken(WxMpKeyComponent.webRefreshAccessToken(openId));
        // 当 [WebAccessToken] 不为空时则设置结果为缓存中的数据
        if (webAccessToken != null && !webAccessToken.isEmpty()) {
            wxMpWebAccessTokenDTO = new WxMpWebAccessTokenDTO();
            wxMpWebAccessTokenDTO.setWebAccessToken(webAccessToken).setWebRefreshToken(webRefreshAccessToken).setOpenId(openId);
        }
        // 当 [WebRefreshAccessToken] 存在时则刷新 [WebAccessToken] 与 [WebRefreshAccessToken]
        if (webRefreshAccessToken != null && !webRefreshAccessToken.isEmpty()) {
            wxMpWebAccessTokenDTO = WxMpUtils.refreshWebAccessToken(wxMpProperties.getAppId(), webRefreshAccessToken);
            wxMpDataStore.setWebAccessToken(WxMpKeyComponent.webAccessToken(openId),
                    wxMpWebAccessTokenDTO.getWebAccessToken(), wxMpWebAccessTokenDTO.getWebAccessTokenExpireTime());
            wxMpDataStore.setWebRefreshAccessToken(WxMpKeyComponent.webRefreshAccessToken(openId),
                    wxMpWebAccessTokenDTO.getWebRefreshToken(), wxMpWebAccessTokenDTO.getWebRefreshTokenExpireTime());
        }
        return wxMpWebAccessTokenDTO;
    }

    @Override
    public WxMpWebAccessTokenDTO getWebAccessTokenByCode(String code) {
        validateProperties();
        WxMpWebAccessTokenDTO wxMpWebAccessTokenDTO = WxMpUtils.getWebAccessToken(wxMpProperties.getAppId(),
                wxMpProperties.getAppSecret(), code);
        wxMpDataStore.setWebAccessToken(WxMpKeyComponent.webAccessToken(wxMpWebAccessTokenDTO.getOpenId()),
                wxMpWebAccessTokenDTO.getWebAccessToken(), wxMpWebAccessTokenDTO.getWebAccessTokenExpireTime());
        wxMpDataStore.setWebRefreshAccessToken(WxMpKeyComponent.webRefreshAccessToken(wxMpWebAccessTokenDTO.getOpenId()),
                wxMpWebAccessTokenDTO.getWebRefreshToken(), wxMpWebAccessTokenDTO.getWebRefreshTokenExpireTime());
        return wxMpWebAccessTokenDTO;
    }

    @Override
    public String getJsApiTicket() {
        validateProperties();
        String wxMpJsApiTicket = wxMpDataStore.getJsApiTicket(WxMpKeyComponent.jsApiTicket());
        if (wxMpJsApiTicket == null || !wxMpJsApiTicket.isEmpty()) {
            WxMpJsApiTicketDTO wxMpJsApiTicketDTO = WxMpUtils.getJsApiTicket(getBaseAccessToken());
            wxMpJsApiTicket = wxMpJsApiTicketDTO.getTicket();
            wxMpDataStore.setJsApiTicket(WxMpKeyComponent.jsApiTicket(), wxMpJsApiTicket,
                    wxMpJsApiTicketDTO.getEffectiveTime());
        }
        return wxMpJsApiTicket;
    }

    @Override
    public WxMpUserInfoDTO getUserInfo(String openId) {
        return WxMpUtils.getUserInfo(getBaseAccessToken(), openId);
    }

    /**
     * 验证配置文件
     */
    private void validateProperties() {
        ValidateUtils.validate(wxMpProperties.getAppId() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[AppId] 未在Yaml进行配置");
        ValidateUtils.validate(wxMpProperties.getAppSecret() == null,
                RequestParamErrorEnum.PARAMETER_EMPTY, "[AppSecret] 未在Yaml进行配置");
    }
}
