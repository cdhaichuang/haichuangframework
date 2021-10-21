package pro.haichuang.framework.redis.key;

/**
 * 框架内置RedisKey
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class RedisKey {

    /**
     * 重复请求Key
     *
     * @param prefix prefix
     * @param value  value
     * @return 重复请求Key
     * @since 1.1.0.211021
     */
    public static String repeatRequest(String prefix, String... value) {
        return prefix.concat("#").concat(String.join("#", value));
    }
}
