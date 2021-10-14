package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.cors.GlobalCorsConfig;

import java.lang.annotation.*;

/**
 * 启用全局跨域配置
 *
 * <p>启用此注解后, 将全局添加跨域配置
 * <p>具体实现请参阅 {@link GlobalCorsConfig}
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see GlobalCorsConfig
 * @since 1.0.0.211014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import(GlobalCorsConfig.class)
public @interface EnableGlobalCorsConfig {
}
