package pro.haichuang.framework.base.config.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

import java.lang.annotation.*;

/**
 * 日志保存切面
 *
 * @author JiYinchuan
 * @version 1.0
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
