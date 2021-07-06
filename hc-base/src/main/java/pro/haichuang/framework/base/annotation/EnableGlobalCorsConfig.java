package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.cors.GlobalCorsConfig;

import java.lang.annotation.*;

/**
 * 启用全局跨域配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import(GlobalCorsConfig.class)
public @interface EnableGlobalCorsConfig {
}
