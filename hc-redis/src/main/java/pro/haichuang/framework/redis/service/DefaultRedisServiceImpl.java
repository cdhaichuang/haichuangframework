package pro.haichuang.framework.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * RedisService默认实现
 *
 * <p>该类为 {@link RedisService} 默认实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@SuppressWarnings({"unchecked", "unused", "UnusedReturnValue", "SpellCheckingInspection"})
public class DefaultRedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public DefaultRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // ============================= Common ============================

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public boolean expire(String key, long expireTime) {
        if (expireTime > 0) {
            Boolean result = redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            return result != null && result;
        }
        return false;
    }

    @Override
    public long getExpire(String key) {
        Long result = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return (result == null) ? -1 : result;
    }

    @Override
    public boolean hasKey(String key) {
        Boolean result = redisTemplate.hasKey(key);
        return result != null && result;
    }

    @Override
    public void del(String... key) {
        if (key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    // ============================ String =============================

    @Override
    public <V> V get(String key) {
        return (V) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long expireTime) {
        if (expireTime > 0) {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    @Override
    public long incr(String key, long factor) {
        if (factor <= 0) {
            throw new RuntimeException("递增因子必须大于 [0]");
        }
        Long result = redisTemplate.opsForValue().increment(key, factor);
        return result == null ? -1 : result;
    }

    @Override
    public long decr(String key, long factor) {
        if (factor <= 0) {
            throw new RuntimeException("递减因子必须大于 [0]");
        }
        Long result = redisTemplate.opsForValue().increment(key, -factor);
        return (result == null) ? -1 : result;
    }

    // ================================ Map =================================

    @Override
    public <V> V hget(String key, String item) {
        return (V) redisTemplate.opsForHash().get(key, item);
    }

    @Override
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hmset(String key, Map<String, Object> map, long expireTime) {
        redisTemplate.opsForHash().putAll(key, map);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    @Override
    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    @Override
    public void hset(String key, String item, Object value, long expireTime) {
        redisTemplate.opsForHash().put(key, item, value);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    @Override
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    @Override
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    @Override
    public double hincr(String key, String item, double factor) {
        return redisTemplate.opsForHash().increment(key, item, factor);
    }

    @Override
    public double hdecr(String key, String item, double factor) {
        return redisTemplate.opsForHash().increment(key, item, -factor);
    }

    // ============================ Set =============================

    @Override
    public <V> Set<V> sGet(String key) {
        Set<Object> result = redisTemplate.opsForSet().members(key);
        return result == null ? null : result.stream().map(v -> (V) v).collect(Collectors.toSet());
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        Boolean result = redisTemplate.opsForSet().isMember(key, value);
        return result != null && result;
    }

    @Override
    public long sSet(String key, Object... values) {
        Long result = redisTemplate.opsForSet().add(key, values);
        return (result == null) ? -1 : result;
    }

    @Override
    public long sSet(String key, long expireTime, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
        return (count == null) ? -1 : count;
    }

    @Override
    public long sGetSize(String key) {
        Long result = redisTemplate.opsForSet().size(key);
        return (result == null) ? -1 : result;
    }

    @Override
    public long sdel(String key, Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return (count == null) ? -1 : count;
    }

    // =============================== List =================================

    @Override
    public <V> List<V> lGet(String key, long start, long end) {
        List<Object> result = redisTemplate.opsForList().range(key, start, end);
        return result == null ? null : result.stream().map(v -> (V) v).collect(Collectors.toList());
    }

    @Override
    public long lGetSize(String key) {
        Long result = redisTemplate.opsForList().size(key);
        return (result == null) ? -1 : result;
    }

    @Override
    public <V> V lGetIndex(String key, long index) {
        return (V) redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void lSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public void lSet(String key, Object value, long expireTime) {
        redisTemplate.opsForList().rightPush(key, value);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    @Override
    public void lSet(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    @Override
    public void lSet(String key, List<Object> value, long expireTime) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    @Override
    public void lEditIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public long lDel(String key, long count, Object value) {
        Long remove = redisTemplate.opsForList().remove(key, count, value);
        return (remove == null) ? -1 : remove;
    }
}
