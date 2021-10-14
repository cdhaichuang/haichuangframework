package pro.haichuang.framework.sdk.wxmp.dto;

import java.io.Serializable;
import java.time.Duration;

/**
 * WxMpBaseAccessTokenDTO
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
public class WxMpBaseAccessTokenDTO implements Serializable {
    private static final long serialVersionUID = -5327256358321392255L;

    /**
     * AccessToken
     */
    private String accessToken;

    /**
     * AccessToken过期时间
     */
    private Duration accessTokenExpireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public WxMpBaseAccessTokenDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Duration getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    public WxMpBaseAccessTokenDTO setAccessTokenExpireTime(Duration accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
        return this;
    }

    @Override
    public String toString() {
        return "WxMpBaseAccessTokenDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", accessTokenExpireTime=" + accessTokenExpireTime +
                '}';
    }
}
