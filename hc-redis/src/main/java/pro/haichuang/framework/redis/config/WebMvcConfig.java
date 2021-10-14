package pro.haichuang.framework.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pro.haichuang.framework.redis.config.autoconfiguration.RedisAutoConfiguration;
import pro.haichuang.framework.redis.config.interceptor.RepeatRequestInterceptor;

/**
 * WebMvcConfig
 *
 * <p>该类为 {@code hc-redis} 的 [WebMvc] 配置
 * <p>该类需要在 {@link RedisAutoConfiguration} 之后加载
 * <p>标注 {@link pro.haichuang.framework.redis.service.RedisService} 注入时才启用本配置
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see RedisAutoConfiguration
 * @since 1.0.0.211014
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
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
