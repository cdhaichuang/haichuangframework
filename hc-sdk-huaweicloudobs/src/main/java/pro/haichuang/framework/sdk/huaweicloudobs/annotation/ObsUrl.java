package pro.haichuang.framework.sdk.huaweicloudobs.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import pro.haichuang.framework.sdk.huaweicloudobs.config.aspect.ObsUrlAspect;

import java.lang.annotation.*;

/**
 * 解析华为云Obs资源
 *
 * <p>将该类标注在实体字段上或 {@code Controller} 层方法形参上, 将在请求时自动去掉OBS请求前缀, 在响应时自动加上OBS请求前缀
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see EnableObsUrlConvert @EnableObsUrlConvert
 * @see ObsUrlAspect
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@ConditionalOnWebApplication
public @interface ObsUrl {
}
