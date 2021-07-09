package pro.haichuang.framework.sdk.wxmp.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.sdk.wxmp.config.properties.WxMpProperties;
import pro.haichuang.framework.sdk.wxmp.controller.SdkWxMpController;
import pro.haichuang.framework.sdk.wxmp.service.DefaultWxMpServiceImpl;
import pro.haichuang.framework.sdk.wxmp.service.WxMpService;

/**
 * 微信公众号自动配置
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WxMpProperties.class)
@Import({
        SdkWxMpController.class
})
public class WxMpAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(WxMpService.class)
    public WxMpService wxMpService() {
        return new DefaultWxMpServiceImpl();
    }
}
