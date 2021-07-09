package pro.haichuang.framework.base.annotation.security;

import java.lang.annotation.*;

/**
 * 自定义角色拦截注解
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @deprecated 该注解暂时废弃, 等待后续更新
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreRole {

    /**
     * @return 允许的 Role 名称
     */
    String[] value();

}
