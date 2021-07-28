package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.mvc.BaseControllerAdvice;
import pro.haichuang.framework.base.validate.phone.PhoneValidator;

import java.lang.annotation.*;

/**
 * 启用全局 Controller 异常处理自动配置
 *
 * <p>启用此注解后, 请求中所有未捕获的异常将全部被统一拦截, 友好的将错误码/错误信息/提示信息返回给客户端
 * <p>注意: 当手动捕获异常后将无法进行异常拦截. 在项目开发过程中建议将异常直接抛出, 由系统自动拦截返回, 而不是手动捕获手动返回
 * <p>具体实现请参阅 {@link BaseControllerAdvice}
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see BaseControllerAdvice
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
