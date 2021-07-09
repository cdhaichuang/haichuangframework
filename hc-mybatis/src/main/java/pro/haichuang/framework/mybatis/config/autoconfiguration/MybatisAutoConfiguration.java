package pro.haichuang.framework.mybatis.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import pro.haichuang.framework.mybatis.config.MybatisPlusConfig;
import pro.haichuang.framework.mybatis.config.PageHelperConfig;
import pro.haichuang.framework.mybatis.config.druid.advert.DruidAdvertConfig;
import pro.haichuang.framework.mybatis.config.properties.MybatisProperties;
import pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService;

/**
 * Mybatis自动配置
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
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
