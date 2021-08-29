package pro.haichuang.framework.sdk.aliyunsms.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.sdk.aliyunsms.config.properties.AliYunSmsProperties;
import pro.haichuang.framework.sdk.aliyunsms.service.AliYunSmsService;
import pro.haichuang.framework.sdk.aliyunsms.service.DefaultAliYunSmsServiceImpl;

/**
 * 阿里云SMS自动配置
 *
 * <p>该类为 {@code hc-sdk-aliyunsms} SDK模块自动配置
 * <p>使用该模块前须在 {@code yaml} 中配置相关参数, 配置参考 {@link AliYunSmsProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AliYunSmsProperties.class)
public class AliYunSmsAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(AliYunSmsService.class)
    public AliYunSmsService aliYunSmsService() {
        return new DefaultAliYunSmsServiceImpl();
    }
}
