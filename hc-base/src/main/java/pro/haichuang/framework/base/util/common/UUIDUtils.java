package pro.haichuang.framework.base.util.common;

import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * UUID工具类
 *
 * <p>该类用于便捷的生成指定格式的 {@code uuid}
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class UUIDUtils {

    /**
     * UUID
     *
     * @return UUID
     * @since 1.1.0.211021
     */
    @NonNull
    public static String random() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * UUID
     *
     * @return UUID
     * @since 1.1.0.211021
     */
    public static long randomOfLang() {
        return Long.parseLong(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    /**
     * Specified Length By UUID
     *
     * @param length length
     * @return UUID
     * @since 1.1.0.211021
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
     * @since 1.1.0.211021
     */
    public static long randomOfLang(int length) {
        return Long.parseLong(random(length));
    }
}
