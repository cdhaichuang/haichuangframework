package pro.haichuang.framework.base.util.common;

import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * UUID工具类
 *
 * <p>该类用于便捷的生成指定格式的 {@code uuid}</p>
 * <p>该类中包含一个内部类 {@link Local}, 用于获取当前线程中唯一的 {@code uuid}, 通常用于日志记录等情况</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see Local
 * @since 1.0.0
 */
public class UUIDUtils {

    /**
     * UUID
     *
     * @return UUID
     */
    @NonNull
    public static String random() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * UUID
     *
     * @return UUID
     */
    public static long randomOfLang() {
        return Long.parseLong(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    /**
     * Specified Length By UUID
     *
     * @param length length
     * @return UUID
     */
    @NonNull
    public static String random(int length) {
        String uuid = UUIDUtils.random();
        return uuid.substring(0, Math.min(length, uuid.length()));
    }

    /**
     * Specified Length By UUID
     *
     * @param length length
     * @return UUID
     */
    public static long randomOfLang(int length) {
        return Long.parseLong(random(length));
    }

    /**
     * 当前线程唯一UUID
     */
    public static class Local {

        private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

        /**
         * 设置当前线程存储的UUID
         */
        public static void init() {
            if (LOCAL.get() == null || LOCAL.get().length() == 0) {
                LOCAL.set(random());
            }
        }

        /**
         * 设置当前线程存储的UUID
         *
         * @param length length
         */
        public static void init(int length) {
            if (LOCAL.get() == null || LOCAL.get().length() == 0) {
                LOCAL.set(random(length));
            }
        }

        /**
         * 获取当前线程存储的UUID, 未获取到当前线程的UUID时会创建一个新的UUID并设置到当前线程中
         *
         * @return UUID.Local
         */
        @NonNull
        public static String get() {
            if (LOCAL.get() == null) {
                init();
            }
            return LOCAL.get();
        }

        /**
         * 移除当前线程存储的UUID
         *
         * <p>因为 {@link ThreadLocal} 底层使用的内部类 {@code ThreadLocalMap} 实现的, 生命周期为当前线程,
         * 所以不执行此方法当线程终止后 {@code ThreadLocalMap} 中的值会被JVM垃圾回收,
         * 但推荐在不需要使用的时候显性的执行此方法, 便于理解</p>
         */
        public static void remove() {
            LOCAL.remove();
        }
    }
}
