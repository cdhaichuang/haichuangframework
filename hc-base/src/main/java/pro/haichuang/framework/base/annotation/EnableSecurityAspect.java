package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.aspect.SecurityAspect;

import java.lang.annotation.*;

/**
 * 启用权限拦截注解
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.base.annotation.security.PrePermission
 * @see pro.haichuang.framework.base.annotation.security.PreRole
 * @deprecated 该注解暂时废弃, 等待后续更新
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import(SecurityAspect.class)
@Deprecated
public @interface EnableSecurityAspect {
}
