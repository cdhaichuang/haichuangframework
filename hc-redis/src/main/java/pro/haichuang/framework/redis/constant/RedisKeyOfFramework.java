package pro.haichuang.framework.redis.constant;

/**
 * 框架内置RedisKey
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 */
public class RedisKeyOfFramework {

    /**
     * 重复请求Key
     *
     * @param prefix prefix
     * @param value  value
     * @return 重复请求Key
     */
    public static String repeatRequest(String prefix, String... value) {
        return prefix.concat("#").concat(String.join("#", value));
    }
}
