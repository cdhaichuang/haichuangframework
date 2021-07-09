package pro.haichuang.framework.sdk.wxmp.service;

import org.springframework.lang.Nullable;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpUserInfoDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * WxMpService
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public interface WxMpService {

    /**
     * 验证微信消息
     *
     * @param signature 微信加密签名, signature结合了开发者填写的token参数和请求中的timestamp参数/nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echoStr   请求响应值
     * @param response  HttpServletResponse
     */
    void verifyWxMessage(String signature, String timestamp, String nonce, String echoStr, HttpServletResponse response);

    /**
     * 获取基础AccessToken
     *
     * @return 基础AccessToken
     */
    String getBaseAccessToken();

    /**
     * 获取网页AccessToken
     *
     * @param openId OpenId
     * @return 网页AccessTokenDTO, 值为 {@code null} 时则视为未登录, 需要重新进行授权
     * @see pro.haichuang.framework.sdk.wxmp.service.WxMpService#getWebAccessTokenByCode(String)
     */
    @Nullable
    WxMpWebAccessTokenDTO getWebAccessTokenByOpenId(String openId);

    /**
     * 获取网页AccessToken
     * 当用户登录成功需要调用此方法进行获取 [OpenId] 并将 [WebAccessToken] 记录到缓存中
     *
     * @param code Code
     * @return 网页AccessTokenDTO
     */
    WxMpWebAccessTokenDTO getWebAccessTokenByCode(String code);

    /**
     * 获取JsApiTicket
     *
     * @return JsApiTicket
     */
    String getJsApiTicket();

    /**
     * 获取用户信息
     *
     * @param openId OpenId
     * @return 用户信息DTO, 当用户未关注当前公众号时值为 {@code null}
     */
    @Nullable
    WxMpUserInfoDTO getUserInfo(String openId);

}
