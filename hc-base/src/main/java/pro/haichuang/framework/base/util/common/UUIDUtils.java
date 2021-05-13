package pro.haichuang.framework.base.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class UUIDUtils {

    /**
     * UUID
     *
     * @return UUID
     */
    public static String random() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * specified length by UUID
     *
     * @param length length
     * @return UUID
     */
    public static String random(int length) {
        String uuid = UUIDUtils.random();
        return uuid.substring(0, Math.min(length, uuid.length()));
    }

    /**
     * 当前线程唯一UUID
     */
    public static class Local {

        private static final Logger logger = LoggerFactory.getLogger(Local.class);
        private static final String LOG_TAG = "UUID.Local";
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
         * 获取当前线程存储的UUID
         *
         * @return UUID.Local
         */
        public static String get() {
            if (LOCAL.get() == null) {
                init();
            }
            return LOCAL.get();
        }

        /**
         * 移除当前线程存储的UUID
         */
        public static void remove() {
            LOCAL.remove();
        }
    }
}
