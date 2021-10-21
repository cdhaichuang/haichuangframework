package pro.haichuang.framework.sdk.wxmp.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.redis.service.RedisService;
import pro.haichuang.framework.sdk.wxmp.key.WxMpKey;

import java.time.Duration;
import java.util.Set;

/**
 * WxMpDataStore Redis实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class WxMpRedisDataStore implements WxMpDataStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxMpRedisDataStore.class);
    private static final String LOG_TAG = "WxMpDataStoreRedis实现";

    @Autowired
    private RedisService redisService;

    @Override
    public void setBaseAccessToken(String key, String baseAccessToken, Duration expireTime) {
        redisService.set(key, baseAccessToken, expireTime.getSeconds());
    }

    @Override
    public String getBaseAccessToken(String key) {
        return redisService.get(key);
    }

    @Override
    public void setWebAccessToken(String key, String webAccessToken, Duration expireTime) {
        redisService.set(key, webAccessToken, expireTime.getSeconds());
    }

    @Override
    public String getWebAccessToken(String key) {
        return redisService.get(key);
    }

    @Override
    public void setWebRefreshAccessToken(String key, String webRefreshAccessToken, Duration expireTime) {
        redisService.set(key, webRefreshAccessToken, expireTime.getSeconds());
    }

    @Override
    public String getWebRefreshAccessToken(String key) {
        return redisService.get(key);
    }

    @Override
    public void setJsApiTicket(String key, String jsApiTicket, Duration expireTime) {
        redisService.set(key, jsApiTicket, expireTime.getSeconds());
    }

    @Override
    public String getJsApiTicket(String key) {
        return redisService.get(key);
    }

    @Override
    public void printAllData() {
        LOGGER.info("[{}] ==================== 开始进行数据打印 ====================", LOG_TAG);
        int baseAccessTokenIndex = 0;
        Set<String> baseAccessTokenRedisKeys = redisService.keys(WxMpKey.baseAccessToken());
        if (baseAccessTokenRedisKeys != null) {
            for (String baseAccessTokenRedisKey : baseAccessTokenRedisKeys) {
                String baseAccessToken = redisService.get(baseAccessTokenRedisKey);
                long baseAccessTokenExpire = redisService.getExpire(baseAccessTokenRedisKey);
                LOGGER.info("[{}] 打印BaseAccessToken数据 [index: {}, Key: {}, lastExpireTime: {}s, value: {}]", LOG_TAG,
                        baseAccessTokenIndex++, baseAccessTokenRedisKey, baseAccessTokenExpire, baseAccessToken);
            }
        }
        int webAccessTokenIndex = 0;
        Set<String> webAccessTokenRedisKeys = redisService.keys(WxMpKey.webAccessToken(""));
        if (webAccessTokenRedisKeys != null) {
            for (String webAccessTokenRedisKey : webAccessTokenRedisKeys) {
                String webAccessToken = redisService.get(webAccessTokenRedisKey);
                long webAccessTokenExpire = redisService.getExpire(webAccessTokenRedisKey);
                LOGGER.info("[{}] 打印WebAccessToken数据 [index: {}, Key: {}, lastExpireTime: {}s, value: {}]", LOG_TAG,
                        webAccessTokenIndex++, webAccessTokenRedisKey, webAccessTokenExpire, webAccessToken);
            }
        }
        int webRefreshAccessTokenIndex = 0;
        Set<String> webRefreshAccessTokenRedisKeys = redisService.keys(WxMpKey.webRefreshAccessToken(""));
        if (webRefreshAccessTokenRedisKeys != null) {
            for (String webRefreshAccessTokenRedisKey : webRefreshAccessTokenRedisKeys) {
                String webRefreshAccessToken = redisService.get(webRefreshAccessTokenRedisKey);
                long webRefreshAccessTokenExpire = redisService.getExpire(webRefreshAccessTokenRedisKey);
                LOGGER.info("[{}] 打印WebRefreshAccessToken数据 [index: {}, Key: {}, lastExpireTime: {}s, value: {}]", LOG_TAG,
                        webRefreshAccessTokenIndex++, webRefreshAccessTokenRedisKey, webRefreshAccessTokenExpire, webRefreshAccessToken);
            }
        }
        int jsApiTicketIndex = 0;
        Set<String> jsApiTicketRedisKeys = redisService.keys(WxMpKey.jsApiTicket());
        if (jsApiTicketRedisKeys != null) {
            for (String jsApiTicketRedisKey : jsApiTicketRedisKeys) {
                String jsApiTicket = redisService.get(jsApiTicketRedisKey);
                long jsApiTicketExpire = redisService.getExpire(jsApiTicketRedisKey);
                LOGGER.info("[{}] 打印JsApiTicketMap数据 [index: {}, Key: {}, lastExpireTime: {}s, value: {}]", LOG_TAG,
                        jsApiTicketIndex++, jsApiTicketRedisKey, jsApiTicketExpire, jsApiTicket);
            }
        }
        LOGGER.info("[{}] ==================== 结束打印 ====================", LOG_TAG);
    }
}
