package pro.haichuang.framework.sdk.wxmp.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.sdk.wxmp.key.WxMpKey;
import pro.haichuang.framework.sdk.wxmp.store.DefaultWxMpDataStore;
import pro.haichuang.framework.sdk.wxmp.store.WxMpDataStore;

/**
 * 微信公众号数据存储自动配置
 *
 * <p>该类为 {@code hc-sdk-huaweicloudobs} SDK模块数据储存自动配置
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @since 1.0.0.211014
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(BaseAutoConfiguration.class)
@ConditionalOnMissingBean(WxMpDataStore.class)
@Import({
        DefaultWxMpDataStore.DelayQueueManager.class
})
public class WxMpStoreAutoConfiguration {

    @Bean
    @Lazy
    public WxMpDataStore wxMpDataStore() {
        return new DefaultWxMpDataStore();
    }

    @Bean
    @Lazy
    public WxMpKey wxMpKeyComponent() {
        return new WxMpKey();
    }
}
