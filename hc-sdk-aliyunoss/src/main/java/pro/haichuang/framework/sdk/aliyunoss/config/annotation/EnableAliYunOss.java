package pro.haichuang.framework.sdk.aliyunoss.config.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;
import pro.haichuang.framework.sdk.aliyunoss.service.AliYunOssService;

import java.lang.annotation.*;

/**
 * 启用SDK阿里云OSS自动配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import({
        AliYunOssProperties.class,
        AliYunOssService.class
})
public @interface EnableAliYunOss {
}
