package pro.haichuang.framework.sdk.aliyunoss.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;
import pro.haichuang.framework.sdk.aliyunoss.service.AliYunOssService;
import pro.haichuang.framework.sdk.aliyunoss.service.DefaultAliYunOssServiceImpl;

/**
 * 阿里云OSS自动配置
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AliYunOssProperties.class)
public class AliYunOssAutoConfiguration {

    @Bean
    @Lazy
    @ConditionalOnMissingBean(AliYunOssService.class)
    public AliYunOssService aliYunOssService() {
        return new DefaultAliYunOssServiceImpl();
    }
}
