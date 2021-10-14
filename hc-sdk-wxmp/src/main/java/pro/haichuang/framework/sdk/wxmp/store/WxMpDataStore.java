package pro.haichuang.framework.sdk.wxmp.store;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Duration;

/**
 * 微信公众号数据存储
 *
 * <p>默认实现为 {@link DefaultWxMpDataStore}, 底层采用延时队列实现, 效率较低
 * <p>建议手动注入该接口, 实现为 {@link WxMpRedisDataStore}, 底层采用 {@code Redis} 实现
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see DefaultWxMpDataStore
 * @see WxMpRedisDataStore
 * @since 1.0.0.211014
 */
public interface WxMpDataStore {

    /**
     * 设置 BaseAccessToken
     *
     * @param key             Key
     * @param baseAccessToken baseAccessToken
     * @param expireTime      过期时间
     * @since 1.0.0.211014
     */
    void setBaseAccessToken(@NonNull String key, @NonNull String baseAccessToken, @NonNull Duration expireTime);

    /**
     * 获取 BaseAccessToken
     *
     * @param key Key
     * @return BaseAccessToken
     * @since 1.0.0.211014
     */
    @Nullable
    String getBaseAccessToken(@NonNull String key);

    /**
     * 设置 WebAccessToken
     *
     * @param key            Key
     * @param webAccessToken WebAccessToken
     * @param expireTime     过期时间
     * @since 1.0.0.211014
     */
    void setWebAccessToken(@NonNull String key, @NonNull String webAccessToken, @NonNull Duration expireTime);

    /**
     * 获取 WebAccessToken
     *
     * @param key Key
     * @return WebAccessToken
     * @since 1.0.0.211014
     */
    @Nullable
    String getWebAccessToken(@NonNull String key);

    /**
     * 设置 WebRefreshAccessToken
     *
     * @param key                   Key
     * @param webRefreshAccessToken WebRefreshAccessToken
     * @param expireTime            过期时间
     * @since 1.0.0.211014
     */
    void setWebRefreshAccessToken(@NonNull String key, @NonNull String webRefreshAccessToken, @NonNull Duration expireTime);

    /**
     * 获取 WebRefreshAccessToken
     *
     * @param key Key
     * @return WebRefreshAccessToken
     * @since 1.0.0.211014
     */
    @Nullable
    String getWebRefreshAccessToken(@NonNull String key);

    /**
     * 设置 JsApiTicket
     *
     * @param key         Key
     * @param jsApiTicket JsApiTicket
     * @param expireTime  过期时间
     * @since 1.0.0.211014
     */
    void setJsApiTicket(@NonNull String key, @NonNull String jsApiTicket, @NonNull Duration expireTime);

    /**
     * 获取 JsApiTicket
     *
     * @param key Key
     * @return JsApiTicket
     * @since 1.0.0.211014
     */
    @Nullable
    String getJsApiTicket(@NonNull String key);

    /**
     * 打印所有数据
     *
     * <p>仅限测试使用
     *
     * @since 1.0.0.211014
     */
    void printAllData();

}
