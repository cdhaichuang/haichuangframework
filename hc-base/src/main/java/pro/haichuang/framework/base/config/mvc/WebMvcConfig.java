package pro.haichuang.framework.base.config.mvc;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pro.haichuang.framework.base.config.mvc.enums.EnumConverterFactory;

import java.util.List;

/**
 * WebMvc配置
 *
 * <p>该类为默认 {@code WebMvc} 配置
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see pro.haichuang.framework.base.config.mvc.FastJsonConfig
 * @see JacksonConfig.LocalDateTimeConverter
 * @see JacksonConfig.LocalDateConverter
 * @see JacksonConfig.LocalTimeConverter
 * @since 1.0.0.211014
 */
@Configuration(proxyBeanMethods = false)
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FastJsonConfig fastJsonConfig;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
        registry.addConverter(new JacksonConfig.LocalDateTimeConverter());
        registry.addConverter(new JacksonConfig.LocalDateConverter());
        registry.addConverter(new JacksonConfig.LocalTimeConverter());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }
}
