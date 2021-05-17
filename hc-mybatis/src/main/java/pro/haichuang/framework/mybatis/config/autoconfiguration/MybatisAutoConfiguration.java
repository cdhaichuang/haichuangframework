package pro.haichuang.framework.mybatis.config.autoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import pro.haichuang.framework.mybatis.config.MybatisPlusConfig;
import pro.haichuang.framework.mybatis.config.PageHelperConfig;
import pro.haichuang.framework.mybatis.config.druid.advert.DruidAdvertConfig;
import pro.haichuang.framework.mybatis.config.properties.MybatisProperties;
import pro.haichuang.framework.mybatis.generate.MybatisGenerateCodeService;

/**
 * CommonAutoConfiguration
 *
 * @author JiYinchuan
 * @version 1.0
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
    @Profile("!prod")
    public MybatisGenerateCodeService mybatisGenerateCode() {
        return new MybatisGenerateCodeService();
    }
}
