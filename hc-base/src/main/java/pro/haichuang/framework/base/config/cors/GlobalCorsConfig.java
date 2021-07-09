package pro.haichuang.framework.base.config.cors;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局跨域配置
 *
 * <p>该类具体实现了全局的跨域配置, 使用了 {@link FilterRegistrationBean} 进行实现,
 * 同时将跨域过滤器 {@link CorsFilter} 的优先级设置为最高(最先执行), 保证了跨域的有效性</p>
 * <p>注意: 该类启用的前置条件为标注了
 * {@link pro.haichuang.framework.base.annotation.EnableGlobalCorsConfig @EnableGlobalCorsConfig} 注解</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see pro.haichuang.framework.base.annotation.EnableGlobalCorsConfig
 */
public class GlobalCorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // 添加CORS配置信息
        Map<String , CorsConfiguration> configurationMap = new HashMap<>(1);
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 允许的域, 为'*'时Cookie无法使用
        corsConfiguration.addAllowedOriginPattern("*");

        // 允许的请求方式
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.HEAD);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.TRACE);

        // 允许的头信息
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

        // 预检请求的有效期
        corsConfiguration.setMaxAge(Duration.ofHours(1));

        // 是否支持安全证书
        corsConfiguration.setAllowCredentials(true);

        // 拦截的路径
        configurationMap.put("/**", corsConfiguration);

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.setCorsConfigurations(configurationMap);

        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(new CorsFilter(configurationSource));
        // 设置 Filter 的优先级为最高(最先执行)
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
