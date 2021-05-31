package pro.haichuang.framework.sdk.wxmp.dto;

import java.io.Serializable;

/**
 * WebAccessTokenDTO
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class WxMpWebAccessTokenDTO implements Serializable {
    private static final long serialVersionUID = 8551944490780144452L;

    /**
     * AccessToken
     */
    private String accessToken;

    /**
     * RefreshToken
     */
    private String refreshToken;

    /**
     * OpenId
     */
    private String openId;

    public String getAccessToken() {
        return accessToken;
    }

    public WxMpWebAccessTokenDTO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public WxMpWebAccessTokenDTO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
        return "WebAccessTokenDTO{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
