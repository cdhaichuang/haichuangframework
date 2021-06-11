package pro.haichuang.framework.redis.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RedisService
 *
 * @author JiYinchuan
 * @version 1.0
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "SpellCheckingInspection"})
public interface RedisService {

    // ============================= Common ============================

    /**
     * 指定缓存失效时间
     *
     * @param key        键
     * @param expireTime 过期时间(秒)
     * @return 返回操作结果
     */
    boolean expire(@NonNull String key, long expireTime);

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 过期时间(秒) 返回0代表为永久有效
     */
    long getExpire(@NonNull String key);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(@NonNull String key);

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    void del(@NonNull String... key);

    // ============================ String =============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @param <V> 值类型
     * @return 值
     */
    @Nullable
    <V> V get(@NonNull String key);

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    boolean set(@NonNull String key, @NonNull Object value);

    /**
     * 普通缓存放入并设置时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    boolean set(@NonNull String key, @NonNull Object value, long expireTime);

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 返回long
     */
    long incr(@NonNull String key, long delta);

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 返回long
     */
    long decr(@NonNull String key, long delta);

    // ================================ Map =================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @param <V>  值类型
     * @return 值
     */
    <V> V hget(@NonNull String key, @NonNull String item);

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    @Nullable
    Map<Object, Object> hmget(@NonNull String key);

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    boolean hmset(@NonNull String key, @NonNull Map<String, Object> map);

    /**
     * HashSet 并设置时间
     *
     * @param key        键
     * @param map        对应多个键值
     * @param expireTime 过期时间(秒)
     * @return true成功 false失败
     */
    boolean hmset(@NonNull String key, @NonNull Map<String, Object> map, long expireTime);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    boolean hset(@NonNull String key, @NonNull String item, @NonNull Object value);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key        键
     * @param item       项
     * @param value      值
     * @param expireTime 过期时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    boolean hset(@NonNull String key, @NonNull String item, @NonNull Object value, long expireTime);

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    void hdel(@NonNull String key, @NonNull Object... item);

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    boolean hHasKey(@NonNull String key, @NonNull String item);

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 返回double
     */
    double hincr(@NonNull String key, @NonNull String item, double by);

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return 返回double
     */
    double hdecr(@NonNull String key, @NonNull String item, double by);

    // ============================ Set =============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @param <V> 值类型
     * @return 返回Set集合
     */
    <V> Set<V> sGet(@NonNull String key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    boolean sHasKey(@NonNull String key, @NonNull Object value);

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    long sSet(@NonNull String key, @NonNull Object... values);

    /**
     * 将set数据放入缓存
     *
     * @param key        键
     * @param expireTime 过期时间(秒)
     * @param values     值 可以是多个
     * @return 成功个数
     */
    long sSetAndTime(@NonNull String key, long expireTime, @NonNull Object... values);

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return set缓存的长度
     */
    long sGetSetSize(@NonNull String key);

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    long setRemove(@NonNull String key, @NonNull Object... values);

    // =============================== List =================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @param <V>   值类型
     * @return 返回 {@code "List<V>"}
     */
    @Nullable
    <V> List<V> lGet(@NonNull String key, long start, long end);

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 返回list缓存的长度
     */
    long lGetListSize(@NonNull String key);

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 {@code "index >= 0"}时， 0 表头，1 第二个元素，依次类推；{@code "index < 0"} 时，-1，表尾，-2倒数第二个元素，依次类推
     * @param <V>   值类型
     * @return 返回Object值
     */
    @Nullable
    <V> V lGetIndex(@NonNull String key, long index);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 返回操作结果
     */
    boolean lSet(@NonNull String key, @NonNull Object value);

    /**
     * 将list放入缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间(秒)
     * @return 返回操作结果
     */
    boolean lSet(@NonNull String key, @NonNull Object value, long expireTime);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 返回 操作结果
     */
    boolean lSet(@NonNull String key, @NonNull List<Object> value);

    /**
     * 将list放入缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间(秒)
     * @return 返回 操作结果
     */
    boolean lSet(@NonNull String key, @NonNull List<Object> value, long expireTime);

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return 返回修改结果
     */
    boolean lUpdateIndex(@NonNull String key, long index, @NonNull Object value);

    /**
     * 移除N个值为value的元素
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    long lRemove(@NonNull String key, long count, @NonNull Object value);

}
