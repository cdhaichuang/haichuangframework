package pro.haichuang.framework.base.util.common;

import cn.hutool.core.util.IdUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import pro.haichuang.framework.base.key.ProjectKey;

import java.util.HashMap;
import java.util.Map;

/**
 * 当前线程唯一变量储存
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
public class ThreadLocalUtils {

    private static final TransmittableThreadLocal<Map<String, Object>> LOCAL = new TransmittableThreadLocal<>();

    /**
     * 获取当前线程唯一ID
     *
     * @return ID
     */
    public static String id() {
        return set(ProjectKey.loggerThreadTtl(), IdUtil.getSnowflake(0, 0).nextIdStr());
    }

    /**
     * 添加当前线程中存储的数据
     *
     * @param key   Key
     * @param value Value
     * @param <T>   Value
     * @return Value
     * @since 1.0.0.211009
     */
    public static <T> T set(String key, T value) {
        Map<String, Object> map = LOCAL.get();
        map = map == null ? new HashMap<>(16) : new HashMap<>(map);
        map.put(key, value);
        LOCAL.set(map);
        return value;
    }

    /**
     * 根据Key获取当前线程存储的值
     *
     * @param key Key
     * @param <T> Value
     * @return Value
     * @since 1.0.0.211009
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T get(final String key) {
        final Map<String, Object> map = LOCAL.get();
        return map == null ? null : (T) map.get(key);
    }

    /**
     * 判断当前线程存储的Map是否包含对应的Key
     *
     * @param key Key
     * @return 是否包含Key
     * @since 1.0.0.211009
     */
    public boolean containsKey(final String key) {
        final Map<String, Object> map = LOCAL.get();
        return map != null && map.containsKey(key);
    }

    /**
     * 根据Key删除当前线程中存储的Map值
     *
     * @param key Key
     */
    public static void remove(String key) {
        final Map<String, Object> map = LOCAL.get();
        map.remove(key);
    }

    /**
     * 移除当前线程存储的Map
     *
     * <p>因为 {@link ThreadLocal} 底层使用的内部类 {@code ThreadLocalMap} 实现的, 生命周期为当前线程,
     * 所以不执行此方法当线程终止后 {@code ThreadLocalMap} 中的值会被JVM垃圾回收,
     * 但推荐在不需要使用的时候显性的执行此方法, 便于理解
     *
     * @since 1.0.0.211009
     */
    public static void clear() {
        LOCAL.remove();
    }
}
