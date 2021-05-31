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
 * @author JiYinchuan
 * @version 1.0
 */
@SuppressWarnings({"unchecked", "unused", "UnusedReturnValue", "SpellCheckingInspection"})
public class DefaultRedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public DefaultRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // ============================= Common ============================

    @Override
    public boolean expire(String key, long time) {
        boolean result = false;
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public long getExpire(String key) {
        Long result = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return (result == null) ? -1 : result;
    }

    @Override
    public boolean hasKey(String key) {
        try {
            Boolean result = redisTemplate.hasKey(key);
            return result != null && result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void del(String... key) {
        if (key != null && key.length > 0) {
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
        return key == null ? null : (V) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean set(String key, Object value, long time) {
        boolean result = false;
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        Long result = redisTemplate.opsForValue().increment(key, delta);
        return result == null ? -1 : result;
    }

    @Override
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        Long result = redisTemplate.opsForValue().increment(key, -delta);
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
    public boolean hmset(String key, Map<String, Object> map) {
        boolean result = false;

        try {
            redisTemplate.opsForHash().putAll(key, map);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean hset(String key, String item, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(key, item, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean hset(String key, String item, Object value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    @Override
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================ Set =============================

    @Override
    public <V> Set<V> sGet(String key) {
        Set<Object> set = null;
        try {
            set = redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set == null ? null : set.stream().map(v -> (V) v).collect(Collectors.toSet());
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        Boolean result = null;
        try {
            result = redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result != null && result;
    }

    @Override
    public long sSet(String key, Object... values) {
        Long result = null;
        try {
            result = redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (result == null) ? -1 : result;
    }

    @Override
    public long sSetAndTime(String key, long time, Object... values) {
        Long count = null;
        try {
            count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (count == null) ? -1 : count;
    }

    @Override
    public long sGetSetSize(String key) {
        Long result = null;
        try {
            result = redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (result == null) ? -1 : result;
    }

    @Override
    public long setRemove(String key, Object... values) {
        Long count = null;
        try {
            count = redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (count == null) ? -1 : count;
    }

    // =============================== List =================================

    @Override
    public <V> List<V> lGet(String key, long start, long end) {
        List<Object> result = null;
        try {
            result = redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result == null ? null : result.stream().map(v -> (V) v).collect(Collectors.toList());
    }

    @Override
    public long lGetListSize(String key) {
        Long result = null;
        try {
            result = redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (result == null) ? -1 : result;
    }

    @Override
    public <V> V lGetIndex(String key, long index) {
        Object result = null;
        try {
            result = redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (V) result;
    }

    @Override
    public boolean lSet(String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean lSet(String key, Object value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean lSet(String key, List<Object> value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean lSet(String key, List<Object> value, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().set(key, index, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public long lRemove(String key, long count, Object value) {
        Long remove = null;
        try {
            remove = redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (remove == null) ? -1 : remove;
    }
}
