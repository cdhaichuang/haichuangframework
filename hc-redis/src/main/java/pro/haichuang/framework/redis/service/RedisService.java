package pro.haichuang.framework.redis.service;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RedisService服务
 *
 * <p>该类为 {@code redis} 操作核心服务接口, 项目中所有 {@code redis} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultRedisServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see DefaultRedisServiceImpl
 * @since 1.0.0.211009
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "SpellCheckingInspection"})
public interface RedisService {

    // ============================= Common ============================

    /**
     * 获取Key
     *
     * @param pattern 正则表达式
     * @return 匹配的Key集合
     * @since 1.0.0.211009
     */
    @Nullable
    Set<String> keys(String pattern);

    /**
     * 指定缓存失效时间
     *
     * @param key        键
     * @param expireTime 过期时间, 单位 [秒]
     * @return 返回操作结果
     * @since 1.0.0.211009
     */
    boolean expire(String key, long expireTime);

    /**
     * 根据Key获取过期时间
     *
     * @param key 键
     * @return 过期时间, 单位 [秒], 不存在则小于 [0]
     * @since 1.0.0.211009
     */
    long getExpire(String key);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return 是否存在 [true: 存在, false: 不存在]
     * @since 1.0.0.211009
     */
    boolean hasKey(String key);

    /**
     * 删除缓存
     *
     * @param key 键
     * @since 1.0.0.211009
     */
    void del(String... key);

    // ============================ String =============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @param <V> 值类型
     * @return 值
     * @since 1.0.0.211009
     */
    @Nullable
    <V> V get(String key);

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @since 1.0.0.211009
     */
    void set(String key, Object value);

    /**
     * 普通缓存放入并设置过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒], 小于等于 [0] 将永不过期
     * @since 1.0.0.211009
     */
    void set(String key, Object value, long expireTime);

    /**
     * 普通缓存递增
     *
     * @param key    键
     * @param factor 递增因子, 必须大于 [0]
     * @return 递增后的值, 不存在则小于 [0]
     * @since 1.0.0.211009
     */
    long incr(String key, long factor);

    /**
     * 普通缓存递减
     *
     * @param key    键
     * @param factor 递减因子, 必须大于 [0]
     * @return 递减后的值, 不存在则小于 [0]
     * @since 1.0.0.211009
     */
    long decr(String key, long factor);

    // ================================ Map =================================

    /**
     * Hash缓存获取项
     *
     * @param key  键
     * @param item 项
     * @param <V>  值类型
     * @return 值
     * @since 1.0.0.211009
     */
    @Nullable
    <V> V hget(String key, String item);

    /**
     * Hash缓存获取所有项
     *
     * @param key 键
     * @return 对应的多个键值
     * @since 1.0.0.211009
     */
    @Nullable
    Map<Object, Object> hmget(String key);

    /**
     * Hash缓存放入所有项
     *
     * @param key 键
     * @param map 对应多个键值
     * @since 1.0.0.211009
     */
    void hmset(String key, Map<String, Object> map);

    /**
     * Hash缓存放入所有项并指定过期时间
     *
     * @param key        键
     * @param map        对应多个键与项
     * @param expireTime 过期时间(秒)
     * @since 1.0.0.211009
     */
    void hmset(String key, Map<String, Object> map, long expireTime);

    /**
     * Hash缓存放入项的值
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @since 1.0.0.211009
     */
    void hset(String key, String item, Object value);

    /**
     * Hash缓存放入项的值并指定过期时间
     *
     * @param key        键
     * @param item       项
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒]
     * @since 1.0.0.211009
     */
    void hset(String key, String item, Object value, long expireTime);

    /**
     * Hash删除项
     *
     * @param key  键
     * @param item 项
     * @since 1.0.0.211009
     */
    void hdel(String key, Object... item);

    /**
     * 判断Hash缓存中是否存在该项的值
     *
     * @param key  键
     * @param item 项
     * @return 是否存在 [true: 存在, false: 不存在]
     * @since 1.0.0.211009
     */
    boolean hHasKey(String key, String item);

    /**
     * Hash缓存项递增
     *
     * @param key    键
     * @param item   项
     * @param factor 递增因子
     * @return 递增后的值
     * @since 1.0.0.211009
     */
    double hincr(String key, String item, double factor);

    /**
     * Hash缓存项递减
     *
     * @param key    键
     * @param item   项
     * @param factor 递减因子
     * @return 递减后的值
     * @since 1.0.0.211009
     */
    double hdecr(String key, String item, double factor);

    // ============================ Set =============================

    /**
     * Set缓存获取
     *
     * @param key 键
     * @param <V> 值类型
     * @return 值
     * @since 1.0.0.211009
     */
    @Nullable
    <V> Set<V> sGet(String key);

    /**
     * Set缓存放入
     *
     * @param key    键
     * @param values 值
     * @return 成功放入个数
     * @since 1.0.0.211009
     */
    long sSet(String key, Object... values);

    /**
     * Set缓存放入并指定过期时间
     *
     * @param key        键
     * @param expireTime 过期时间, 单位 [秒]
     * @param values     值
     * @return 成功放入个数
     * @since 1.0.0.211009
     */
    long sSet(String key, long expireTime, Object... values);

    /**
     * Set缓存长度获取
     *
     * @param key 键
     * @return Set缓存长度获取
     * @since 1.0.0.211009
     */
    long sGetSize(String key);

    /**
     * 判断Set缓存是否存在值
     *
     * @param key   键
     * @param value 值
     * @return 是否存在 [true: 存在, false: 不存在]
     * @since 1.0.0.211009
     */
    boolean sHasKey(String key, Object value);

    /**
     * Set缓存删除指定值
     *
     * @param key    键
     * @param values 值
     * @return 成功删除的个数
     * @since 1.0.0.211009
     */
    long sdel(String key, Object... values);

    // =============================== List =================================

    /**
     * List缓存获取
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @param <V>   值类型
     * @return 值
     * @since 1.0.0.211009
     */
    @Nullable
    <V> List<V> lGet(String key, long start, long end);

    /**
     * List缓存长度获取
     *
     * @param key 键
     * @return List缓存长度
     * @since 1.0.0.211009
     */
    long lGetSize(String key);

    /**
     * List缓存指定索引获取
     *
     * @param key   键
     * @param index 索引, 小于 [0] 时则表示从末尾开始计算
     * @param <V>   值类型
     * @return 值
     * @since 1.0.0.211009
     */
    @Nullable
    <V> V lGetIndex(String key, long index);

    /**
     * List缓存放入
     *
     * @param key   键
     * @param value 值
     * @since 1.0.0.211009
     */
    void lSet(String key, Object value);

    /**
     * List缓存放入并指定过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒]
     * @since 1.0.0.211009
     */
    void lSet(String key, Object value, long expireTime);

    /**
     * List缓存放入
     *
     * @param key   键
     * @param value 值
     * @since 1.0.0.211009
     */
    void lSet(String key, List<Object> value);

    /**
     * List缓存批量放入
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒]
     * @since 1.0.0.211009
     */
    void lSet(String key, List<Object> value, long expireTime);

    /**
     * List缓存根据索引修改
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @since 1.0.0.211009
     */
    void lEditIndex(String key, long index, Object value);

    /**
     * List缓存删除Count个值为value的值
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 成功删除个数
     * @since 1.0.0.211009
     */
    long lDel(String key, long count, Object value);

}
