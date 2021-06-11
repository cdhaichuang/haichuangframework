package pro.haichuang.framework.sdk.wxmp.dto;

import java.io.Serializable;
import java.time.Duration;

/**
 * WebAccessTokenDTO
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class WxMpWebAccessTokenDTO implements Serializable {
    private static final long serialVersionUID = -9121624711237083374L;

    /**
     * WebAccessToken
     */
    private String webAccessToken;

    /**
     * WebAccessToken过期时间
     */
    private Duration webAccessTokenExpireTime;

    /**
     * WebRefreshToken
     */
    private String webRefreshToken;

    /**
     * OpenId
     */
    private String openId;

    public String getWebAccessToken() {
        return webAccessToken;
    }

    public WxMpWebAccessTokenDTO setWebAccessToken(String webAccessToken) {
        this.webAccessToken = webAccessToken;
        return this;
    }

    public Duration getWebAccessTokenExpireTime() {
        return webAccessTokenExpireTime;
    }

    public WxMpWebAccessTokenDTO setWebAccessTokenExpireTime(Duration webAccessTokenExpireTime) {
        this.webAccessTokenExpireTime = webAccessTokenExpireTime;
        return this;
    }

    public String getWebRefreshToken() {
        return webRefreshToken;
    }

    public WxMpWebAccessTokenDTO setWebRefreshToken(String webRefreshToken) {
        this.webRefreshToken = webRefreshToken;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public WxMpWebAccessTokenDTO setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public String toString() {
        return "WxMpWebAccessTokenDTO{" +
                "webAccessToken='" + webAccessToken + '\'' +
                ", webAccessTokenExpireTime=" + webAccessTokenExpireTime +
                ", webRefreshToken='" + webRefreshToken + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
