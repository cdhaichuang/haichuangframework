package pro.haichuang.framework.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis服务
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SuppressWarnings({"unchecked", "unused", "UnusedReturnValue", "SpellCheckingInspection"})
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // =============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 返回操作结果
     */
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

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        Long result = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return (result == null) ? -1 : result;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            Boolean result = redisTemplate.hasKey(key);
            return result != null && result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    // ============================String=============================

    /**
     * 普通缓存获取
     * @param key 键
     * @param <V> 值类型
     * @return 值
     */
    public <V> V get(String key) {
        return key == null ? null : (V) redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
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

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
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

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 返回long
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        Long result = redisTemplate.opsForValue().increment(key, delta);
        return result == null ? -1 : result;
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 返回long
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        Long result = redisTemplate.opsForValue().increment(key, -delta);
        return (result == null) ? -1 : result;
    }

    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @param <V> 值类型
     * @return 值
     */
    public <V> V hget(String key, String item) {
        return (V) redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
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

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
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

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
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

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
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

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 返回double
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return 返回double
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @param <V> 值类型
     * @return 返回Set集合
     */
    public <V> Set<V> sGet(String key) {
        Set<Object> set = null;
        try {
            set = redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set == null ? null : set.stream().map(v -> (V) v).collect(Collectors.toSet());
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        Boolean result = null;
        try {
            result = redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result != null && result;
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        Long result = null;
        try {
            result = redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (result == null) ? -1 : result;
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
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

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return set缓存的长度
     */
    public long sGetSetSize(String key) {
        Long result = null;
        try {
            result = redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (result == null) ? -1 : result;
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        Long count = null;
        try {
            count = redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (count == null) ? -1 : count;
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @param <V> 值类型
     * @return 返回 {@code "List<V>"}
     */
    public <V> List<V> lGet(String key, long start, long end) {
        List<Object> result = null;
        try {
            result = redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result == null ? null : result.stream().map(v -> (V) v).collect(Collectors.toList());
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 返回list缓存的长度
     */
    public long lGetListSize(String key) {
        Long result = null;
        try {
            result = redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (result == null) ? -1 : result;
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 {@code "index >= 0"}时， 0 表头，1 第二个元素，依次类推；{@code "index < 0"} 时，-1，表尾，-2倒数第二个元素，依次类推
     * @param <V> 值类型
     * @return 返回Object值
     */
    public <V> V lGetIndex(String key, long index) {
        Object result = null;
        try {
            result = redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (V) result;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 返回操作结果
     */
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

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 返回操作结果
     */
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

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 返回 操作结果
     */
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

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 返回 操作结果
     */
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

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return 返回修改结果
     */
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

    /**
     * 移除N个值为value的元素
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
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
