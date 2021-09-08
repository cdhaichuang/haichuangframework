package pro.haichuang.framework.redis.component;

/**
 * 框架内置RedisKey
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisKeyComponent {

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
