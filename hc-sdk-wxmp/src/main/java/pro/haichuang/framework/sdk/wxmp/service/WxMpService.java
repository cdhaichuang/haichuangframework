package pro.haichuang.framework.sdk.wxmp.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * WxMpService
 *
 * @author JiYinchuan
 * @version 1.0
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
    void verifyWxMessage(@NonNull String signature, @NonNull String timestamp, @NonNull String nonce,
                         @NonNull String echoStr, @NonNull HttpServletResponse response);

    /**
     * 获取基础AccessToken
     *
     * @return 基础AccessToken
     */
    @NonNull
    String getBaseAccessToken();

    /**
     * 获取网页AccessToken
     *
     * @param openId openId
     * @return 网页AccessTokenDTO, 值为 [null] 时则视为未登录, 需要重新进行授权
     * @see pro.haichuang.framework.sdk.wxmp.service.WxMpService#getWebAccessTokenByCode(String)
     */
    @Nullable
    WxMpWebAccessTokenDTO getWebAccessTokenByOpenId(@NonNull String openId);

    /**
     * 获取网页AccessToken
     * 当用户登录成功需要调用此方法进行获取 [OpenId] 并将 [WebAccessToken] 记录到缓存中
     *
     * @param code Code
     * @return 网页AccessTokenDTO
     */
    @NonNull
    WxMpWebAccessTokenDTO getWebAccessTokenByCode(@NonNull String code);

    /**
     * 获取JsApiTicket
     *
     * @return JsApiTicket
     */
    @NonNull
    String getJsApiTicket();

}
