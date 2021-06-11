package pro.haichuang.framework.sdk.wxmp.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.base.enums.abnormal.client.AuthorityAbnormalEnum;
import pro.haichuang.framework.base.enums.abnormal.client.RequestParamAbnormalEnum;
import pro.haichuang.framework.base.response.ResultVO;
import pro.haichuang.framework.base.util.common.ResponseUtils;
import pro.haichuang.framework.base.util.common.ValidateUtils;
import pro.haichuang.framework.redis.service.RedisService;
import pro.haichuang.framework.sdk.wxmp.config.properties.WxMpProperties;
import pro.haichuang.framework.sdk.wxmp.config.store.WxMpDataStore;
import pro.haichuang.framework.sdk.wxmp.constant.WxMpKeyPrefix;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpBaseAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpJsApiTicketDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.util.WxMpUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

/**
 * WxMpService默认实现
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class DefaultWxMpServiceImpl implements WxMpService {

    /**
     * WebRefreshToken默认过期时间
     */
    private static final long WEB_REFRESH_TOKEN_EXPIRE_SECOND = Duration.ofDays(30).getSeconds();

    @Autowired
    private WxMpProperties wxMpProperties;
    @Autowired
    private WxMpDataStore wxMpDataStore;
    @Autowired
    private RedisService redisService;

    @SneakyThrows
    @Override
    public void verifyWxMessage(String signature, String timestamp, String nonce,
                                String echoStr, HttpServletResponse response) {
        validateProperties();
        ValidateUtils.validate(wxMpProperties.getToken() == null,
                RequestParamAbnormalEnum.PARAMETER_EMPTY, "[Token] 未在Yaml进行配置");
        if (WxMpUtils.checkSignature(wxMpProperties.getToken(), signature, timestamp, nonce)) {
            ResponseUtils.originalWrite(response, echoStr);
        } else {
            ResponseUtils.write(response, ResultVO.other(AuthorityAbnormalEnum.ACCESS_BLOCKED));
        }
    }

    @Override
    public String getBaseAccessToken() {
        validateProperties();
        String baseAccessToken = redisService.get(WxMpKeyPrefix.BASE_ACCESS_TOKEN);
        // 当缓存中 [WxMpBaseAccessTokenDTO] 为空时则向微信发起请求获取 [WxMpBaseAccessTokenDTO] 并存入缓存中
        if (baseAccessToken == null || baseAccessToken.isEmpty()) {
            WxMpBaseAccessTokenDTO wxMpBaseAccessTokenDTO = WxMpUtils.getBaseAccessToken(wxMpProperties.getAppId(), wxMpProperties.getAppSecret());
            baseAccessToken = wxMpBaseAccessTokenDTO.getAccessToken();
            wxMpDataStore.setBaseAccessToken(WxMpKeyPrefix.BASE_ACCESS_TOKEN, wxMpBaseAccessTokenDTO.getAccessToken(), wxMpBaseAccessTokenDTO.getAccessTokenExpireTime());
            // redisService.set(WxMpRedisKeyPrefix.BASE_ACCESS_TOKEN, wxMpBaseAccessTokenDTO.getAccessToken(),
            //         wxMpBaseAccessTokenDTO.getAccessTokenExpireTime().getSeconds());
        }
        return baseAccessToken;
    }

    @Override
    public WxMpWebAccessTokenDTO getWebAccessTokenByOpenId(String openId) {
        validateProperties();
        WxMpWebAccessTokenDTO result = null;


        String webAccessToken = redisService.get(WxMpKeyPrefix.WEB_ACCESS_TOKEN.concat(openId));
        String webRefreshAccessToken = redisService.get(WxMpKeyPrefix.WEB_REFRESH_ACCESS_TOKEN.concat(openId));
        // 当 [WebAccessToken] 不为空时则设置结果为缓存中的数据
        if (webAccessToken != null && !webAccessToken.isEmpty()) {
            result = new WxMpWebAccessTokenDTO();
            result.setWebAccessToken(webAccessToken).setWebRefreshToken(webRefreshAccessToken).setOpenId(openId);
        }
        // 当 [WebRefreshAccessToken] 存在时则刷新 [WebAccessToken] 与 [WebRefreshAccessToken]
        if (webRefreshAccessToken != null && !webRefreshAccessToken.isEmpty()) {
            result = WxMpUtils.refreshWebAccessToken(wxMpProperties.getAppId(), webRefreshAccessToken);
            redisService.set(WxMpKeyPrefix.WEB_ACCESS_TOKEN.concat(openId),
                    result.getWebAccessToken(), result.getWebAccessTokenExpireTime().getSeconds());
            redisService.set(WxMpKeyPrefix.WEB_REFRESH_ACCESS_TOKEN.concat(openId),
                    result.getWebRefreshToken(), WEB_REFRESH_TOKEN_EXPIRE_SECOND);
        }
        return result;
    }

    @Override
    public WxMpWebAccessTokenDTO getWebAccessTokenByCode(String code) {
        validateProperties();
        WxMpWebAccessTokenDTO wxMpWebAccessTokenDTO = WxMpUtils.getWebAccessToken(wxMpProperties.getAppId(),
                wxMpProperties.getAppSecret(), code);
        redisService.set(WxMpKeyPrefix.WEB_ACCESS_TOKEN.concat(wxMpWebAccessTokenDTO.getOpenId()),
                wxMpWebAccessTokenDTO.getWebAccessToken(), wxMpWebAccessTokenDTO.getWebAccessTokenExpireTime().getSeconds());
        redisService.set(WxMpKeyPrefix.WEB_REFRESH_ACCESS_TOKEN.concat(wxMpWebAccessTokenDTO.getOpenId()),
                wxMpWebAccessTokenDTO.getWebRefreshToken(), WEB_REFRESH_TOKEN_EXPIRE_SECOND);
        return wxMpWebAccessTokenDTO;
    }

    @Override
    public String getJsApiTicket() {
        validateProperties();
        WxMpJsApiTicketDTO wxMpJsApiTicketDTO = redisService.get(WxMpKeyPrefix.JS_API_TICKET);
        if (wxMpJsApiTicketDTO == null) {
            wxMpJsApiTicketDTO = WxMpUtils.getJsApiTicket(getBaseAccessToken());
            redisService.set(WxMpKeyPrefix.JS_API_TICKET, wxMpJsApiTicketDTO.getTicket(),
                    wxMpJsApiTicketDTO.getEffectiveTime().getSeconds());
        }
        return wxMpJsApiTicketDTO.getTicket();
    }

    /**
     * 验证配置文件
     */
    private void validateProperties() {
        ValidateUtils.validate(wxMpProperties.getAppId() == null,
                RequestParamAbnormalEnum.PARAMETER_EMPTY, "[AppId] 未在Yaml进行配置");
        ValidateUtils.validate(wxMpProperties.getAppSecret() == null,
                RequestParamAbnormalEnum.PARAMETER_EMPTY, "[AppSecret] 未在Yaml进行配置");
    }
}
