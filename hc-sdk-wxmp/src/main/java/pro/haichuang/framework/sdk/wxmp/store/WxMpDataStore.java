package pro.haichuang.framework.sdk.wxmp.store;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Duration;

/**
 * 微信公众号数据存储
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
public interface WxMpDataStore {

    /**
     * 设置 BaseAccessToken
     *
     * @param key             Key
     * @param baseAccessToken baseAccessToken
     * @param expireTime      过期时间
     */
    void setBaseAccessToken(@NonNull String key, @NonNull String baseAccessToken, @NonNull Duration expireTime);

    /**
     * 获取 BaseAccessToken
     *
     * @param key Key
     * @return BaseAccessToken
     */
    @Nullable
    String getBaseAccessToken(@NonNull String key);

    /**
     * 设置 WebAccessToken
     *
     * @param key            Key
     * @param webAccessToken WebAccessToken
     * @param expireTime     过期时间
     */
    void setWebAccessToken(@NonNull String key, @NonNull String webAccessToken, @NonNull Duration expireTime);

    /**
     * 获取 WebAccessToken
     *
     * @param key Key
     * @return WebAccessToken
     */
    @Nullable
    String getWebAccessToken(@NonNull String key);

    /**
     * 设置 WebRefreshAccessToken
     *
     * @param key                   Key
     * @param webRefreshAccessToken WebRefreshAccessToken
     * @param expireTime            过期时间
     */
    void setWebRefreshAccessToken(@NonNull String key, @NonNull String webRefreshAccessToken, @NonNull Duration expireTime);

    /**
     * 获取 WebRefreshAccessToken
     *
     * @param key Key
     * @return WebRefreshAccessToken
     */
    @Nullable
    String getWebRefreshAccessToken(@NonNull String key);

    /**
     * 设置 JsApiTicket
     *
     * @param key         Key
     * @param jsApiTicket JsApiTicket
     * @param expireTime  过期时间
     */
    void setJsApiTicket(@NonNull String key, @NonNull String jsApiTicket, @NonNull Duration expireTime);

    /**
     * 获取 JsApiTicket
     *
     * @param key Key
     * @return JsApiTicket
     */
    @Nullable
    String getJsApiTicket(@NonNull String key);

    /**
     * 打印所有数据
     * 仅限测试使用
     */
    default void printAllData() {
    }
}
