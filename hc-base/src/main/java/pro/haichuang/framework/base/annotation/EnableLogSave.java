package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;
import pro.haichuang.framework.base.config.aspect.LogSaveAspect;
import pro.haichuang.framework.base.config.interceptor.AbstractLogSave;

import java.lang.annotation.*;

/**
 * 启用权限拦截注解
 *
 * <p>启用此注解后, 会将带有 {@link LogSave @LogSave} 注解的方法相关请求与响应进行持久化记录
 * <p>注意: 需要自定义类继承 {@link AbstractLogSave} 抽象类, 在自定义类中实现响应持久化逻辑
 *
 * @author JiYinchuan
 * @see LogSaveAspect
 * @see AbstractLogSave
 * @since 1.1.0.211021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ConditionalOnWebApplication
@Import(LogSaveAspect.class)
public @interface EnableLogSave {
}
