package pro.haichuang.framework.sdk.huaweicloudobs.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import pro.haichuang.framework.sdk.huaweicloudobs.config.aspect.ObsUrlAspect;

import java.lang.annotation.*;

/**
 * 解析华为云对象存储资源
 *
 * <p>将该类标注在实体字段上或 {@code Controller} 层方法形参上, 将在请求时自动去掉OBS请求前缀, 在响应时自动加上OBS请求前缀
 * <hr>
 * <p>注意: 该注解标注的字段必须包含 {@code setXxx} 和 {@code getXxx} 方法, 且方法返回值必须为 {@code void}
 *
 * @author JiYinchuan
 * @see EnableObsUrlConvert @EnableObsUrlConvert
 * @see ObsUrlAspect
 * @since 1.1.0.211021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@ConditionalOnWebApplication
public @interface ObsUrl {
}
