package pro.haichuang.framework.sdk.wxmp.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.sdk.wxmp.store.DefaultWxMpDataStore;
import pro.haichuang.framework.sdk.wxmp.store.WxMpDataStore;

/**
 * 微信公众号数据存储自动配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(WxMpDataStore.class)
@Import({
        DefaultWxMpDataStore.DelayQueueManager.class
})
public class WxMpStoreAutoConfiguration {

    @Bean
    public WxMpDataStore wxMpDataStore() {
        return new DefaultWxMpDataStore();
    }
}
