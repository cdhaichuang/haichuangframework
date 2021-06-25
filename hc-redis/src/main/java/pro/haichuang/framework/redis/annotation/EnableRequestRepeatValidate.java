package pro.haichuang.framework.redis.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.redis.config.WebMvcConfig;

import java.lang.annotation.*;

/**
 * 启用重复请求校验
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import({WebMvcConfig.class})
public @interface EnableRequestRepeatValidate {
}
