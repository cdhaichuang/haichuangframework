package pro.haichuang.framework.base.config.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.aspect.LogSaveAspect;

import java.lang.annotation.*;

/**
 * 启用权限拦截注解
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import(LogSaveAspect.class)
public @interface EnableLogSave {
}
