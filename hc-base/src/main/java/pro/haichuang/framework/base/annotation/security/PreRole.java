package pro.haichuang.framework.base.annotation.security;

import java.lang.annotation.*;

/**
 * 自定义角色拦截注解
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreRole {

    /**
     * @return 允许的Role名称
     */
    String[] value();

}
