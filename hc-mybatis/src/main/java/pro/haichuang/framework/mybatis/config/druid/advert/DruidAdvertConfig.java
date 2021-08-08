package pro.haichuang.framework.mybatis.config.druid.advert;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.haichuang.framework.mybatis.config.druid.advert.filter.DruidAdvertFilter;

/**
 * Druid广告拦截配置
 *
 * <p>该过滤器主要用于去掉 [druid] 网页中的底部广告内容
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see DruidAdvertFilter
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
public class DruidAdvertConfig {

    @Bean
    public FilterRegistrationBean<DruidAdvertFilter> removeDruidAdvert(DruidStatProperties properties) {
        // 获取Web监控页面参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js配置路径
        String pattern = StringUtils.isNotBlank(config.getUrlPattern()) ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");

        FilterRegistrationBean<DruidAdvertFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DruidAdvertFilter());
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
