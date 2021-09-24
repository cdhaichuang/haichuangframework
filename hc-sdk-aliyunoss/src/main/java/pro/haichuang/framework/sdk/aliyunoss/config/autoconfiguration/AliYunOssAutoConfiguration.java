package pro.haichuang.framework.sdk.aliyunoss.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.sdk.aliyunoss.config.aspect.OssUrlAspect;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;
import pro.haichuang.framework.sdk.aliyunoss.service.AliYunOssService;
import pro.haichuang.framework.sdk.aliyunoss.service.DefaultAliYunOssServiceImpl;

/**
 * 阿里云OSS自动配置
 *
 * <p>该类为 {@code hc-sdk-aliyunoss} SDK模块自动配置
 * <p>使用该模块前须在 {@code yaml} 中配置相关参数, 配置参考 {@link AliYunOssProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see AliYunOssProperties
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(BaseAutoConfiguration.class)
@EnableConfigurationProperties(AliYunOssProperties.class)
@Import({
        OssUrlAspect.class
})
public class AliYunOssAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(AliYunOssService.class)
    public AliYunOssService aliYunOssService() {
        return new DefaultAliYunOssServiceImpl();
    }
}
