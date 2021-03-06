package pro.haichuang.framework.redis.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.redis.config.WebMvcConfig;
import pro.haichuang.framework.redis.config.interceptor.RepeatRequestInterceptor;

import java.lang.annotation.*;

/**
 * 启用重复请求校验
 *
 * <p>启用此注解后, 在 [Controller] 层方法上标注 {@link RepeatRequestValid @RepeatRequestValid} 注解,
 * 将采用 {@code redis} 的方式进行请求验证, 避免重复请求
 * <p>具体实现请参阅 {@link RepeatRequestInterceptor}
 *
 * @author JiYinchuan
 * @see RepeatRequestValid
 * @see RepeatRequestInterceptor
 * @since 1.1.0.211021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import({WebMvcConfig.class})
public @interface EnableRequestRepeatValidate {
}
