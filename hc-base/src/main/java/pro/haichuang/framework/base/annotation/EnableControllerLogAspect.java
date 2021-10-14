package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.aspect.LogAspect;

import java.lang.annotation.*;

/**
 * 启用日志切面
 *
 * <p>启用此注解后, 会将所有带有 {@link io.swagger.annotations.ApiOperation @ApiOperation} 注解的的方法相关请求响应数据记录在日志中,
 * 从而更好的去根据日志排查请求记录
 * <p>具体实现请参阅 {@link LogAspect}
 *
 * @author JiYinchuan
 * @version 1.0.0.211014
 * @see LogAspect
 * @since 1.0.0.211014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import({LogAspect.class})
public @interface EnableControllerLogAspect {
}
