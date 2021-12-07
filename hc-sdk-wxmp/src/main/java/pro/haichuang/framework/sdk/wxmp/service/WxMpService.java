package pro.haichuang.framework.sdk.wxmp.service;

import org.springframework.lang.Nullable;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpUserInfoDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.exception.WxMpExecuteException;

import javax.servlet.http.HttpServletResponse;

/**
 * WxMpService
 *
 * <p>该类为 {@code wxmp} 第三方操作核心接口, 项目中所有 {@code wxmp} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultWxMpServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
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
     * @since 1.1.0.211021
     */
    void verifyWxMessage(String signature, String timestamp, String nonce, String echoStr, HttpServletResponse response);

    /**
     * 获取基础AccessToken
     *
     * @return 基础AccessToken
     * @throws WxMpExecuteException 微信公众号执行异常
     * @since 1.1.0.211021
     */
    String getBaseAccessToken() throws WxMpExecuteException;

    /**
     * 获取网页AccessToken
     *
     * @param openId OpenId
     * @return 网页AccessTokenDTO, 值为 {@code null} 时则视为未登录, 需要重新进行授权
     * @throws WxMpExecuteException 微信公众号执行异常
     * @see pro.haichuang.framework.sdk.wxmp.service.WxMpService#getWebAccessTokenByCode(String)
     * @since 1.1.0.211021
     */
    @Nullable
    WxMpWebAccessTokenDTO getWebAccessTokenByOpenId(String openId) throws WxMpExecuteException;

    /**
     * 获取网页AccessToken
     * 当用户登录成功需要调用此方法进行获取 [OpenId] 并将 [WebAccessToken] 记录到缓存中
     *
     * @param code Code
     * @return 网页AccessTokenDTO
     * @throws WxMpExecuteException 微信公众号执行异常
     * @since 1.1.0.211021
     */
    WxMpWebAccessTokenDTO getWebAccessTokenByCode(String code) throws WxMpExecuteException;

    /**
     * 获取JsApiTicket
     *
     * @return JsApiTicket
     * @throws WxMpExecuteException 微信公众号执行异常
     * @since 1.1.0.211021
     */
    String getJsApiTicket() throws WxMpExecuteException;

    /**
     * 获取用户信息
     *
     * @param openId OpenId
     * @return 用户信息DTO, 当用户未关注当前公众号时值为 {@code null}
     * @throws WxMpExecuteException 微信公众号执行异常
     * @since 1.1.0.211021
     */
    @Nullable
    WxMpUserInfoDTO getUserInfo(String openId) throws WxMpExecuteException;

}
