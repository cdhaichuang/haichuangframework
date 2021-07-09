package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

import java.lang.annotation.*;

/**
 * 日志保存切面
 *
 * <p>该注解配合 {@link EnableLogSave @EnableLogSave} 注解使用, 当开启了 {@code @EnableLogSave} 后,
 * 在 [Controller] 方法上标注该注解, 从而实现日志持久化记录</p>
 * <p>注意: 需要自定义类继承 {@link pro.haichuang.framework.base.config.interceptor.AbstractLogSave} 抽象类,
 * 在自定义类中实现响应持久化逻辑</p>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see EnableLogSave
 * @see pro.haichuang.framework.base.config.interceptor.AbstractLogSave
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@ConditionalOnWebApplication
public @interface LogSave {

    /**
     * 描述信息
     *
     * @return 描述信息
     */
    String message();

}
