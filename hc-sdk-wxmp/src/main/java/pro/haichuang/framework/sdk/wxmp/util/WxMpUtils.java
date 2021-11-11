package pro.haichuang.framework.sdk.wxmp.util;

import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.enums.base.SexEnum;
import pro.haichuang.framework.base.exception.ThirdPartyException;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpBaseAccessTokenDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpJsApiTicketDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpUserInfoDTO;
import pro.haichuang.framework.sdk.wxmp.dto.WxMpWebAccessTokenDTO;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号工具类
 *
 * <p>该类为 {@code wxmp} 相关操作工具类, 提供了对 {@code wxmp} 相关操作的封装
 * <p><a href="https://developers.weixin.qq.com/doc/offiaccount/Getting_Started/Overview.html">点击查看官方文档</a>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@SuppressWarnings("SpellCheckingInspection")
public class WxMpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxMpUtils.class);
    private static final String LOG_TAG = "sdk-wxmp-util";

    /**
     * 错误码名称
     */
    public static final String ERROR_CODE_NAME = "errcode";

    /**
     * 错误信息名称
     */
    public static final String ERROR_MESSAGE_NAME = "errmsg";

    /**
     * WebRefreshToken默认过期时间
     * <p>
     * 默认过期时间为 [30] 天, 考虑到系统容错能力设置为 [29] 天, 避免正在处理时三方微信内部过期, 导致不可用
     */
    private static final Duration WEB_REFRESH_TOKEN_EXPIRE = Duration.ofDays(30).minusDays(1);

    /**
     * 验证签名
     *
     * @param token     Token
     * @param signature 微信加密签名, signature结合了开发者填写的token参数和请求中的timestamp参数/nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return 验证是否成功 {false: 失败, true: 成功}
     * @since 1.1.0.211021
     */
    public static boolean checkSignature(@NonNull String token, @NonNull String signature, @NonNull String timestamp, @NonNull String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String c : arr) {
            content.append(c);
        }
        String hexString = DigestUtils.sha1Hex(content.toString().getBytes());
        return !hexString.isEmpty() && hexString.equals(signature);
    }

    /**
     * 获取基础AccessToken
     *
     * @param appId     AppId
     * @param appSecret AppSecret
     * @return 基础AccessToken
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpBaseAccessTokenDTO getBaseAccessToken(@NonNull String appId, @NonNull String appSecret) {
        return getBaseAccessToken(appId, appSecret, HttpGlobalConfig.getTimeout());
    }

    /**
     * 获取基础AccessToken
     *
     * @param appId     AppId
     * @param appSecret AppSecret
     * @param timout    超时时间
     * @return 基础AccessToken
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpBaseAccessTokenDTO getBaseAccessToken(@NonNull String appId, @NonNull String appSecret, int timout) {
        String baseUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> params = new HashMap<>(3);
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("grant_type", "client_credential");
        JSONObject resultJson = JSONObject.parseObject(HttpUtil.get(baseUrl, params, timout));
        if (validateFailResult(resultJson)) {
            LOGGER.error("[{}] 获取基础AccessToken异常 [response: {}]", LOG_TAG, resultJson.toJSONString());
            throw new ThirdPartyException(resultJson.getString(ERROR_CODE_NAME), resultJson.getString(ERROR_MESSAGE_NAME));
        }
        LOGGER.info("[{}] 成功获取基础AccessToken [response: {}]", LOG_TAG, resultJson.toJSONString());
        Duration expiresIn = Duration.ofSeconds(resultJson.getLongValue("expires_in"));
        // 考虑到系统容错能力, 此处在过期时间基础上提前 [5] 分钟过期, 避免正在处理时三方微信内部过期, 导致不可用
        expiresIn = expiresIn.minusMinutes(5);
        return new WxMpBaseAccessTokenDTO()
                .setAccessToken(resultJson.getString("access_token"))
                .setAccessTokenExpireTime(expiresIn);
    }

    /**
     * 获取网页AccessToken
     *
     * @param appId     AppId
     * @param appSecret AppSecret
     * @param code      Code
     * @return 网页AccessTokenDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpWebAccessTokenDTO getWebAccessToken(@NonNull String appId, @NonNull String appSecret, @NonNull String code) {
        return getWebAccessToken(appId, appSecret, code, HttpGlobalConfig.getTimeout());
    }

    /**
     * 获取网页AccessToken
     *
     * @param appId     AppId
     * @param appSecret AppSecret
     * @param code      Code
     * @param timout    超时时间
     * @return 网页AccessTokenDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpWebAccessTokenDTO getWebAccessToken(@NonNull String appId, @NonNull String appSecret, @NonNull String code, int timout) {
        String baseUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String, Object> params = new HashMap<>(4);
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        JSONObject resultJson = JSONObject.parseObject(HttpUtil.get(baseUrl, params, timout));
        if (validateFailResult(resultJson)) {
            LOGGER.error("[{}] 获取网页AccessToken异常 [response: {}]", LOG_TAG, resultJson.toJSONString());
            throw new ThirdPartyException(resultJson.getString(ERROR_CODE_NAME), resultJson.getString(ERROR_MESSAGE_NAME));
        }
        LOGGER.info("[{}] 成功获取网页AccessToken [response: {}]", LOG_TAG, resultJson.toJSONString());
        Duration expiresIn = Duration.ofSeconds(resultJson.getLongValue("expires_in"));
        // 考虑到系统容错能力, 此处在过期时间基础上提前 [5] 分钟过期, 避免正在处理时三方微信内部过期, 导致不可用
        expiresIn = expiresIn.minusMinutes(5);
        return new WxMpWebAccessTokenDTO()
                .setWebAccessToken(resultJson.getString("access_token"))
                .setWebAccessTokenExpireTime(expiresIn)
                .setWebRefreshToken(resultJson.getString("refresh_token"))
                .setWebRefreshTokenExpireTime(WEB_REFRESH_TOKEN_EXPIRE)
                .setOpenId(resultJson.getString("openid"));
    }

    /**
     * 刷新网页AccessToken
     *
     * @param appId        AppId
     * @param refreshToken RefreshToken
     * @return 网页AccessTokenDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpWebAccessTokenDTO refreshWebAccessToken(@NonNull String appId, @NonNull String refreshToken) {
        return refreshWebAccessToken(appId, refreshToken, HttpGlobalConfig.getTimeout());
    }

    /**
     * 刷新网页AccessToken
     *
     * @param appId        AppId
     * @param refreshToken RefreshToken
     * @param timout       超时时间
     * @return 网页AccessTokenDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpWebAccessTokenDTO refreshWebAccessToken(@NonNull String appId, @NonNull String refreshToken, int timout) {
        String baseUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        Map<String, Object> params = new HashMap<>(3);
        params.put("appid", appId);
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        JSONObject resultJson = JSONObject.parseObject(HttpUtil.get(baseUrl, params, timout));
        if (validateFailResult(resultJson)) {
            LOGGER.error("[{}] 刷新网页AccessToken异常 [response: {}]", LOG_TAG, resultJson.toJSONString());
            throw new ThirdPartyException(resultJson.getString(ERROR_CODE_NAME), resultJson.getString(ERROR_MESSAGE_NAME));
        }
        LOGGER.info("[{}] 成功刷新网页AccessToken [response: {}]", LOG_TAG, resultJson.toJSONString());
        Duration expiresIn = Duration.ofSeconds(resultJson.getLongValue("expires_in"));
        // 考虑到系统容错能力, 此处在过期时间基础上提前 [5] 分钟过期, 避免正在处理时三方微信内部过期, 导致不可用
        expiresIn = expiresIn.minusMinutes(5);
        return new WxMpWebAccessTokenDTO()
                .setWebAccessToken(resultJson.getString("access_token"))
                .setWebAccessTokenExpireTime(expiresIn)
                .setWebRefreshToken(resultJson.getString("refresh_token"))
                .setWebRefreshTokenExpireTime(WEB_REFRESH_TOKEN_EXPIRE)
                .setOpenId(resultJson.getString("openid"));
    }

    /**
     * 根据AccessToken获取JsApiTicket
     *
     * @param accessToken AccessToken
     * @return JsApiTicketDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpJsApiTicketDTO getJsApiTicket(@NonNull String accessToken) {
        return getJsApiTicket(accessToken, HttpGlobalConfig.getTimeout());
    }

    /**
     * 根据AccessToken获取JsApiTicket
     *
     * @param accessToken AccessToken
     * @param timout      超时时间
     * @return JsApiTicketDTO
     * @since 1.1.0.211021
     */
    @NonNull
    public static WxMpJsApiTicketDTO getJsApiTicket(@NonNull String accessToken, int timout) {
        String baseUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        Map<String, Object> params = new HashMap<>(2);
        params.put("access_token", accessToken);
        params.put("type", "jsapi");
        JSONObject resultJson = JSONObject.parseObject(HttpUtil.get(baseUrl, params, timout));
        if (validateFailResult(resultJson)) {
            LOGGER.error("[{}] 获取jsApi_ticket异常 [response: {}]", LOG_TAG, resultJson.toJSONString());
            throw new ThirdPartyException(resultJson.getString(ERROR_CODE_NAME), resultJson.getString(ERROR_MESSAGE_NAME));
        }
        LOGGER.info("[{}] 成功获取jsApi_ticket [response: {}]", LOG_TAG, resultJson.toJSONString());
        Duration expiresIn = Duration.ofSeconds(resultJson.getLongValue("expires_in"));
        // 考虑到系统容错能力, 此处在过期时间基础上提前 [5] 分钟过期, 避免正在处理时三方微信内部过期, 导致不可用
        expiresIn = expiresIn.minusMinutes(5);
        return new WxMpJsApiTicketDTO()
                .setTicket(resultJson.getString("ticket"))
                .setEffectiveTime(expiresIn);
    }

    /**
     * 根据AccessToken和openId获取用户信息
     *
     * @param accessToken AccessToken
     * @param openId      OpenId
     * @return 用户信息
     * @since 1.1.0.211021
     */
    @Nullable
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static WxMpUserInfoDTO getUserInfo(@NonNull String accessToken, @NonNull String openId) {
        String baseUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
        Map<String, Object> params = new HashMap<>(3);
        params.put("access_token", accessToken);
        params.put("openid", openId);
        params.put("lang", "zh_CN");
        JSONObject resultJson = JSONObject.parseObject(HttpUtil.get(baseUrl, params));
        if (validateFailResult(resultJson)) {
            LOGGER.error("[{}] 获取用户信息异常 [response: {}]", LOG_TAG, resultJson.toJSONString());
            throw new ThirdPartyException(resultJson.getString(ERROR_CODE_NAME), resultJson.getString(ERROR_MESSAGE_NAME));
        }
        LOGGER.info("[{}] 成功获取获取用户信息 [response: {}]", LOG_TAG, resultJson.toJSONString());
        if (resultJson.getIntValue("subscribe") == 0) {
            LOGGER.warn("[{}] 获取用户信息失败-该用户未关注此公众号 [openId: {}, response: {}]", LOG_TAG, openId, resultJson.toJSONString());
            return null;
        }
        return new WxMpUserInfoDTO()
                .setSubscribe(resultJson.getInteger("subscribe"))
                .setOpenId(resultJson.getString("openid"))
                .setNickname(resultJson.getString("nickname"))
                .setSex(resultJson.getIntValue("sex") == 1 ? SexEnum.MAN : resultJson.getIntValue("sex") == 2 ? SexEnum.WOMAN : SexEnum.UNKNOWN)
                .setCity(resultJson.getString("city"))
                .setCountry(resultJson.getString("country"))
                .setProvince(resultJson.getString("province"))
                .setLanguage(resultJson.getString("language"))
                .setHeadImageUrl(resultJson.getString("headimgurl"))
                .setSubscribeTime(resultJson.getLong("subscribe_time"))
                .setUnionId(resultJson.getString("unionid"))
                .setRemark(resultJson.getString("remark"))
                .setGroupId(resultJson.getInteger("groupid"))
                .setTagIdList(resultJson.getJSONArray("tagid_list").toJavaList(Integer.class))
                .setSubscribeScene(WxMpUserInfoDTO.SubscribeSceneEnum.resolve(resultJson.getString("subscribe_scene")))
                .setQrScene(resultJson.getInteger("qr_scene"))
                .setQrSceneStr(resultJson.getString("qr_scene_str"));
    }

    /**
     * 验证失败返回结果
     *
     * @param jsonObject 返回JSON对象
     * @return {false: 验证失败, true: 验证成功}
     * @since 1.1.0.211021
     */
    public static boolean validateFailResult(@NonNull JSONObject jsonObject) {
        Integer errorCode = jsonObject.getInteger(ERROR_CODE_NAME);
        String errorMessage = jsonObject.getString(ERROR_MESSAGE_NAME);
        return ObjectUtils.anyNotNull(errorCode, errorMessage) && !errorCode.equals(0);
    }
}
