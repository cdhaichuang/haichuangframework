package pro.haichuang.framework.sdk.wxmp.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.sdk.wxmp.config.properties.WxMpProperties;
import pro.haichuang.framework.sdk.wxmp.controller.SdkWxMpController;
import pro.haichuang.framework.sdk.wxmp.service.DefaultWxMpServiceImpl;
import pro.haichuang.framework.sdk.wxmp.service.WxMpService;

/**
 * 微信公众号自动配置
 *
 * <p>该类为 {@code hc-sdk-wxmp} SDK模块自动配置
 * <p>使用该模块前须在 {@code yaml} 中配置相关参数, 配置参考 {@link WxMpProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(BaseAutoConfiguration.class)
@EnableConfigurationProperties(WxMpProperties.class)
@Import({
        SdkWxMpController.class
})
public class WxMpAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(WxMpService.class)
    public WxMpService wxMpService() {
        return new DefaultWxMpServiceImpl();
    }
}
