package pro.haichuang.framework.mybatis.config.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import pro.haichuang.framework.base.config.autoconfiguration.BaseAutoConfiguration;
import pro.haichuang.framework.mybatis.config.MybatisPlusConfig;
import pro.haichuang.framework.mybatis.config.PageHelperConfig;
import pro.haichuang.framework.mybatis.config.druid.advert.DruidAdvertConfig;
import pro.haichuang.framework.mybatis.config.properties.MybatisProperties;
import pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService;

/**
 * Mybatis自动配置
 *
 * <p>该类为 {@code hc-mybatis} 模块核心自动配置类
 * <p>可以在 {@code yaml} 配置文件中指定 {@link MybatisProperties} 中相关的属性
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @since 1.0.0.211009
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(BaseAutoConfiguration.class)
@EnableConfigurationProperties(MybatisProperties.class)
@ConditionalOnProperty(
        prefix = "haichuang.mybatis",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true
)
@Import({
        DruidAdvertConfig.class,
        MybatisPlusConfig.class,
        PageHelperConfig.class
})
public class MybatisAutoConfiguration {

    @Bean
    @Lazy
    @Profile("!prod")
    public MybatisGenerateCodeService mybatisGenerateCode() {
        return new MybatisGenerateCodeService();
    }
}
