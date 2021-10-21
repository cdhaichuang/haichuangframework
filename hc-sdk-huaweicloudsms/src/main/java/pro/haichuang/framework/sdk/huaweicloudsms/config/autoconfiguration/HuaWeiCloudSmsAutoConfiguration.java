package pro.haichuang.framework.sdk.huaweicloudsms.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.sdk.huaweicloudsms.config.properties.HuaWeiCloudSmsProperties;
import pro.haichuang.framework.sdk.huaweicloudsms.service.DefaultHuaWeiCloudSmsServiceImpl;
import pro.haichuang.framework.sdk.huaweicloudsms.service.HuaWeiCloudSmsService;

/**
 * 华为云SMS自动配置
 *
 * <p>该类为 {@code hc-sdk-huaweicloudsms} SDK模块自动配置
 * <p>使用该模块前须在 {@code yaml} 中配置相关参数, 配置参考 {@link HuaWeiCloudSmsProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(BaseAutoConfiguration.class)
@EnableConfigurationProperties(HuaWeiCloudSmsProperties.class)
public class HuaWeiCloudSmsAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(HuaWeiCloudSmsService.class)
    public HuaWeiCloudSmsService aliYunSmsService() {
        return new DefaultHuaWeiCloudSmsServiceImpl();
    }
}
