package pro.haichuang.framework.sdk.wxmp.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.lang.NonNull;
import pro.haichuang.framework.sdk.wxmp.enums.error.WxMpDelayQueueErrorEnum;
import pro.haichuang.framework.sdk.wxmp.exception.WxMpDelayQueueException;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * WxMpDataStore默认实现
 * 数据默认采用 {@link java.util.concurrent.ConcurrentHashMap} 进行存储
 * 数据自动过期采用 {@link java.util.concurrent.DelayQueue} 延时队列实现
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class DefaultWxMpDataStore implements WxMpDataStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWxMpDataStore.class);
    private static final String LOG_TAG = "WxMpDataStore默认实现";

    private static final Map<String, String> BASE_ACCESS_TOKEN_MAP = new ConcurrentHashMap<>();
    private static final Map<String, String> WEB_ACCESS_TOKEN_MAP = new ConcurrentHashMap<>();
    private static final Map<String, String> WEB_REFRESH_ACCESS_TOKEN_MAP = new ConcurrentHashMap<>();
    private static final Map<String, String> JS_API_TICKET_MAP = new ConcurrentHashMap<>();

    private static final DelayQueue<DelayBase> DELAY_QUEUE = new DelayQueue<>();
    private static boolean DELAY_IS_ERROR = true;

    @Override
    public void setBaseAccessToken(@NonNull String key, @NonNull String baseAccessToken, @NonNull Duration expireTime) {
        validateDelayError();
        BASE_ACCESS_TOKEN_MAP.put(key, baseAccessToken);
        DELAY_QUEUE.removeIf(delayBase -> delayBase.key.equals(key));
        DELAY_QUEUE.put(new DelayBase(DelayDataType.BASE_ACCESS_TOKEN, key, expireTime));
    }

    @Override
    public String getBaseAccessToken(@NonNull String key) {
        validateDelayError();
        return BASE_ACCESS_TOKEN_MAP.get(key);
    }

    @Override
    public void setWebAccessToken(@NonNull String key, @NonNull String webAccessToken, @NonNull Duration expireTime) {
        validateDelayError();
        WEB_ACCESS_TOKEN_MAP.put(key, webAccessToken);
        DELAY_QUEUE.removeIf(delayBase -> delayBase.key.equals(key));
        DELAY_QUEUE.put(new DelayBase(DelayDataType.WEB_ACCESS_TOKEN, key, expireTime));
    }

    @Override
    public String getWebAccessToken(@NonNull String key) {
        validateDelayError();
        return WEB_ACCESS_TOKEN_MAP.get(key);
    }

    @Override
    public void setWebRefreshAccessToken(@NonNull String key, @NonNull String webRefreshAccessToken, @NonNull Duration expireTime) {
        validateDelayError();
        WEB_REFRESH_ACCESS_TOKEN_MAP.put(key, webRefreshAccessToken);
        DELAY_QUEUE.removeIf(delayBase -> delayBase.key.equals(key));
        DELAY_QUEUE.put(new DelayBase(DelayDataType.WEB_REFRESH_ACCESS_TOKEN, key, expireTime));
    }

    @Override
    public String getWebRefreshAccessToken(@NonNull String key) {
        validateDelayError();
        return WEB_REFRESH_ACCESS_TOKEN_MAP.get(key);
    }

    @Override
    public void setJsApiTicket(@NonNull String key, @NonNull String jsApiTicket, @NonNull Duration expireTime) {
        validateDelayError();
        JS_API_TICKET_MAP.put(key, jsApiTicket);
        DELAY_QUEUE.removeIf(delayBase -> delayBase.key.equals(key));
        DELAY_QUEUE.put(new DelayBase(DelayDataType.JS_API_TICKET, key, expireTime));
    }

    @Override
    public String getJsApiTicket(@NonNull String key) {
        validateDelayError();
        return JS_API_TICKET_MAP.get(key);
    }

    @Override
    public void printAllData() {
        validateDelayError();
        LOGGER.info("[{}] ==================== 开始进行数据打印 ====================", LOG_TAG);
        int baseAccessTokenMapIndex = 0;
        for (Map.Entry<String, String> entry : BASE_ACCESS_TOKEN_MAP.entrySet()) {
            LOGGER.info("[{}] 打印BaseAccessToken数据 [index: {}, Key: {}, value: {}]", LOG_TAG,
                    baseAccessTokenMapIndex++, entry.getKey(), entry.getValue());
        }
        int webAccessTokenMapIndex = 0;
        for (Map.Entry<String, String> entry : WEB_ACCESS_TOKEN_MAP.entrySet()) {
            LOGGER.info("[{}] 打印WebAccessToken数据 [index: {}, Key: {}, value: {}]", LOG_TAG,
                    webAccessTokenMapIndex++, entry.getKey(), entry.getValue());
        }
        int webRefreshAccessTokenMapIndex = 0;
        for (Map.Entry<String, String> entry : WEB_REFRESH_ACCESS_TOKEN_MAP.entrySet()) {
            LOGGER.info("[{}] 打印WebRefreshAccessToken数据 [index: {}, Key: {}, value: {}]", LOG_TAG,
                    webRefreshAccessTokenMapIndex++, entry.getKey(), entry.getValue());
        }
        int jsApiTicketMapIndex = 0;
        for (Map.Entry<String, String> entry : JS_API_TICKET_MAP.entrySet()) {
            LOGGER.info("[{}] 打印JsApiTicketMap数据 [index: {}, Key: {}, value: {}]", LOG_TAG,
                    jsApiTicketMapIndex++, entry.getKey(), entry.getValue());
        }
        LOGGER.info("[{}] ==================== 开始进行延时队列打印 ====================", LOG_TAG);
        int delayQueueIndex = 0;
        for (DelayBase delayBase : DELAY_QUEUE) {
            LOGGER.info("[{}] 打印DelayQueue数据 [index: {}, dataType: {}, Key: {}, lastExpireTime: {}ms]", LOG_TAG,
                    delayQueueIndex++, delayBase.dataType, delayBase.key,
                    delayBase.expireMillis - System.currentTimeMillis());
        }
        LOGGER.info("[{}] ==================== 结束打印 ====================", LOG_TAG);
    }

    /**
     * 验证延时队列是否存在异常
     *
     * @since 1.1.0.211021
     */
    private void validateDelayError() {
        if (DELAY_IS_ERROR) {
            throw new WxMpDelayQueueException(WxMpDelayQueueErrorEnum.TERMINATION_ERROR);
        }
    }

    /**
     * 延时队列管理, 用于处理相关数据过期自动删除功能
     *
     * @author JiYinchuan
     * @since 1.1.0.211021
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public static class DelayQueueManager implements CommandLineRunner {

        @Override
        public void run(String... args) {
            try {
                DELAY_IS_ERROR = false;
                while (true) {
                    DelayBase take = DELAY_QUEUE.take();
                    switch (take.dataType) {
                        case BASE_ACCESS_TOKEN:
                            LOGGER.info("[{}] 开始销毁BaseAccessToken [key: {}, value: {}]", LOG_TAG,
                                    take.key, BASE_ACCESS_TOKEN_MAP.get(take.key));
                            BASE_ACCESS_TOKEN_MAP.remove(take.key);
                            break;
                        case WEB_ACCESS_TOKEN:
                            LOGGER.info("[{}] 开始销毁WebAccessToken [key: {}, value: {}]", LOG_TAG,
                                    take.key, WEB_ACCESS_TOKEN_MAP.get(take.key));
                            WEB_ACCESS_TOKEN_MAP.remove(take.key);
                            break;
                        case WEB_REFRESH_ACCESS_TOKEN:
                            LOGGER.info("[{}] 开始销毁WebRefreshAccessToken [key: {}, value: {}]", LOG_TAG,
                                    take.key, WEB_REFRESH_ACCESS_TOKEN_MAP.get(take.key));
                            WEB_REFRESH_ACCESS_TOKEN_MAP.remove(take.key);
                            break;
                        default:
                    }
                }
            } catch (Exception e) {
                DELAY_IS_ERROR = true;
                LOGGER.error("[{}] 执行延时队列失败", LOG_TAG, e);
                throw new WxMpDelayQueueException(WxMpDelayQueueErrorEnum.UNKNOWN_ERROR);
            }
        }
    }

    /**
     * 延时实体, 用户存放每条数据相关信息
     *
     * @author JiYinchuan
     * @since 1.1.0.211021
     */
    static class DelayBase implements Delayed {

        /**
         * 延时数据类型
         */
        private final DelayDataType dataType;
        /**
         * 延时对应Key
         */
        private final String key;
        /**
         * 过期时间
         * 单位 [毫秒]
         */
        private final long expireMillis;

        /**
         * 构造器
         *
         * @param dataType 延时数据类型
         * @param key      延时对应Key
         * @param duration 过期时间, 单位 [毫秒]
         * @since 1.1.0.211021
         */
        public DelayBase(DelayDataType dataType, String key, Duration duration) {
            this.dataType = dataType;
            this.key = key;
            this.expireMillis = System.currentTimeMillis() + duration.toMillis();
        }

        @Override
        public long getDelay(@NonNull TimeUnit unit) {
            return expireMillis - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.SECONDS));
        }
    }

    /**
     * 延时数据类型
     *
     * @since 1.1.0.211021
     */
    enum DelayDataType {

        /**
         * BaseAccessToken
         */
        BASE_ACCESS_TOKEN,

        /**
         * WebAccessToken
         */
        WEB_ACCESS_TOKEN,

        /**
         * WebRefreshAccessToken
         */
        WEB_REFRESH_ACCESS_TOKEN,

        /**
         * JsApiTicket
         */
        JS_API_TICKET

    }
}
