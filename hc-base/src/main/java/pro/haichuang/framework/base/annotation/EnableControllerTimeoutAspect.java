package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.aspect.RequestTimeoutAspect;

import java.lang.annotation.*;

/**
 * 启用请求超时切面
 *
 * <p>启用此注解后, 会将所有带有 {@link io.swagger.annotations.ApiOperation @ApiOperation} 注解的的方法开启请求超时切面监听,
 * 超时则会抛出请求超时异常, 开发人员需要及时解决请求超时问题
 * <p>具体实现请参阅 {@link RequestTimeoutAspect}
 *
 * @author JiYinchuan
 * @see RequestTimeoutAspect
 * @since 1.1.0.211021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import({RequestTimeoutAspect.class})
public @interface EnableControllerTimeoutAspect {
}
