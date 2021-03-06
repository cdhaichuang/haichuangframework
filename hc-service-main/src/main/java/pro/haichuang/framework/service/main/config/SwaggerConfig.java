package pro.haichuang.framework.service.main.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import pro.haichuang.framework.base.config.swagger.factory.SwaggerFactory;
import pro.haichuang.framework.base.config.swagger.SwaggerInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;

/**
 * Swagger配置
 *
 * @author JiYinchuan
 * @see SwaggerFactory
 * @since 1.1.0.211021
 */
@SpringBootConfiguration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket createRestApi() {
        return SwaggerFactory.createRestApi(SwaggerInfo.builder().openApiExtensionResolver(openApiExtensionResolver).build());
    }
}
