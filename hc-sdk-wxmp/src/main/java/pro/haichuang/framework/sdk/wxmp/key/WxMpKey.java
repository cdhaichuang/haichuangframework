package pro.haichuang.framework.sdk.wxmp.key;

import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.base.config.properties.BaseConfigProperties;

import javax.annotation.PostConstruct;

/**
 * 微信公众号数据Key
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see pro.haichuang.framework.sdk.wxmp.service.DefaultWxMpServiceImpl
 * @since 1.0.0.211014
 */
public class WxMpKey {

    /**
     * BaseAccessToken
     */
    private static final String BASE_ACCESS_TOKEN = "FRAMEWORK:WXMP:BASE_ACCESS_TOKEN";

    /**
     * WebAccessToken
     */
    private static final String WEB_ACCESS_TOKEN = "FRAMEWORK:WXMP:WEB_ACCESS_TOKEN:";

    /**
     * WebRefreshAccessToken
     */
    private static final String WEB_REFRESH_ACCESS_TOKEN = "FRAMEWORK:WXMP:WEB_REFRESH_ACCESS_TOKEN:";

    /**
     * JsApiTicket
     */
    private static final String JS_API_TICKET = "FRAMEWORK:WXMP:JS_API_TICKET";

    /**
     * 项目Code, 在Bean加载完后设置
     *
     * @see #setProjectCode()
     * @since 1.0.0.211014
     */
    private static String PROJECT_CODE = "";

    @Autowired
    private BaseConfigProperties baseConfigProperties;


    @PostConstruct
    public void setProjectCode() {
        String projectCode = baseConfigProperties.getProjectCode();
        if (projectCode != null && !projectCode.isEmpty()) {
            WxMpKey.PROJECT_CODE = projectCode;
        }
    }

    /**
     * 获取BaseAccessTokenKey
     *
     * @return BaseAccessTokenKey
     * @since 1.0.0.211014
     */
    public static String baseAccessToken() {
        return PROJECT_CODE.concat(BASE_ACCESS_TOKEN);
    }

    /**
     * 获取WebAccessTokenKey
     *
     * @param key Key
     * @return WebAccessTokenKey
     * @since 1.0.0.211014
     */
    public static String webAccessToken(String key) {
        return PROJECT_CODE.concat(WEB_ACCESS_TOKEN.concat(key));
    }

    /**
     * 获取WebRefreshAccessTokenKey
     *
     * @param key Key
     * @return WebRefreshAccessTokenKey
     * @since 1.0.0.211014
     */
    public static String webRefreshAccessToken(String key) {
        return PROJECT_CODE.concat(WEB_REFRESH_ACCESS_TOKEN.concat(key));
    }

    /**
     * 获取JsApiTicket
     *
     * @return JsApiTicket
     * @since 1.0.0.211014
     */
    public static String jsApiTicket() {
        return PROJECT_CODE.concat(JS_API_TICKET);
    }
}
