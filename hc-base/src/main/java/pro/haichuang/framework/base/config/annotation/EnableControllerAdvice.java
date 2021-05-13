package pro.haichuang.framework.base.config.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.mvc.BaseControllerAdvice;
import pro.haichuang.framework.base.validate.phone.PhoneValidator;

import java.lang.annotation.*;

/**
 * 启用全局Controller异常处理自动配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import({
        PhoneValidator.class,
        BaseControllerAdvice.class
})
public @interface EnableControllerAdvice {
}
