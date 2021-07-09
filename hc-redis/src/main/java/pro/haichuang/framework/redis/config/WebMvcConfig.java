package pro.haichuang.framework.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pro.haichuang.framework.redis.config.properties.RedisProperties;
import pro.haichuang.framework.redis.interceptor.RepeatRequestInterceptor;

/**
 * WebMvcConfig
 * 标注 {@link AutoConfigureAfter} 注解后会在指定自动配置类后加载
 * 标注 {@link pro.haichuang.framework.redis.service.RedisService} 注入时才启用本配置
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see org.springframework.boot.autoconfigure.AutoConfigureBefore
 * @see org.springframework.boot.autoconfigure.AutoConfigureOrder
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisProperties.class)
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RepeatRequestInterceptor repeatRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatRequestInterceptor).addPathPatterns("/**");
    }

    @Bean
    public RepeatRequestInterceptor repeatRequestInterceptor() {
        return new RepeatRequestInterceptor();
    }
}
