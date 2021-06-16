package pro.haichuang.framework.sdk.wxmp.store;

import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.redis.service.RedisService;

import java.time.Duration;

/**
 * WxMpDataStore Redis实现
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class WxMpRedisDataStore implements WxMpDataStore {

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
}
