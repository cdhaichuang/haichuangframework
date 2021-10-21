package pro.haichuang.framework.base.key;

/**
 * 项目键常量
 *
 * <p>该类主要用于存储项目键获取
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class ProjectKey {

    /**
     * 获取日志线程TTL-Key
     *
     * @return 日志线程TTL-Key
     * @since 1.1.0.211021
     */
    public static String loggerThreadTtl() {
        return "LoggerKey";
    }
}
