package pro.haichuang.framework.redis.annotation;

import pro.haichuang.framework.redis.config.interceptor.RepeatRequestInterceptor;

import java.lang.annotation.*;

/**
 * 重复请求自定义注解
 *
 * <p>该注解配合 {@link EnableRequestRepeatValidate @EnableRequestRepeatValidate} 注解使用, 当开启了 {@code @EnableRequestRepeatValidate} 后,
 * 在 [Controller] 方法上标注该注解, 从而实现重复请求拦截
 * <p>具体实现请参阅 {@link RepeatRequestInterceptor}
 *
 * @author JiYinchuan
 * @since 1.0.0
 * @version 1.0.0
 * @see EnableRequestRepeatValidate
 * @see RepeatRequestInterceptor
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatRequestValid {

    /**
     * 设置重复请求的间隔时间, 单位 [秒]
     * 默认 {@code "3s"}
     *
     * @return 间隔时间
     */
    int value() default 3;

    /**
     * 设置RedisKey前缀
     * 默认 {@code "REPEAT"}
     *
     * @return Redis前缀
     */
    String preKey() default "REPEAT";
}
