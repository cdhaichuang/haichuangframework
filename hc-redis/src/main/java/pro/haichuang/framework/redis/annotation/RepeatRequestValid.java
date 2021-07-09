package pro.haichuang.framework.redis.annotation;

import java.lang.annotation.*;

/**
 * 重复请求自定义注解
 * 基于 Redis 实现, 使用时请先引入 {@code hc-redis} 依赖, 否则不生效
 *
 * @author JiYinchuan
 * @version 1.0.0
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatRequestValid {

    /**
     * 设置重复请求的间隔时间, 单位s
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
