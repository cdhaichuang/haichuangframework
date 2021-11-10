package pro.haichuang.framework.sdk.huaweicloudobs.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.sdk.huaweicloudobs.config.aspect.ObsUrlAspect;
import pro.haichuang.framework.sdk.huaweicloudobs.config.properties.HuaWeiCloudObsProperties;
import pro.haichuang.framework.sdk.huaweicloudobs.service.DefaultHuaWeiCloudObsServiceImpl;
import pro.haichuang.framework.sdk.huaweicloudobs.service.HuaWeiCloudObsService;

/**
 * 华为云OBS自动配置
 *
 * <p>该类为 {@code hc-sdk-huaweicloudobs} SDK模块自动配置
 * <p>使用该模块前须在 {@code yaml} 中配置相关参数, 配置参考 {@link HuaWeiCloudObsProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @see HuaWeiCloudObsProperties
 * @since 1.1.0.211021
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(BaseAutoConfiguration.class)
@EnableConfigurationProperties(HuaWeiCloudObsProperties.class)
@Import({
        ObsUrlAspect.class
})
public class HuaWeiCloudObsAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(HuaWeiCloudObsService.class)
    public HuaWeiCloudObsService huaWeiCloudObsService() {
        return new DefaultHuaWeiCloudObsServiceImpl();
    }
}
