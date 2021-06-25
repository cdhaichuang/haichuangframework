package pro.haichuang.framework.base.annotation.security;

import java.lang.annotation.*;

/**
 * 自定义权限拦截注解
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PrePermission {

    /**
     * @return 允许的Permission名称
     */
    String[] value();

}
