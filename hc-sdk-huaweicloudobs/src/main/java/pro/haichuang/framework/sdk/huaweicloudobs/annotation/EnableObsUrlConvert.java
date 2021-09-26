package pro.haichuang.framework.sdk.huaweicloudobs.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import pro.haichuang.framework.sdk.huaweicloudobs.config.aspect.ObsUrlAspect;

import java.lang.annotation.*;

/**
 * 启用华为云OBS地址解析
 *
 * <p>该注解应标注在 {@code Controller} 层方法上, 代表该请求启用OBS地址解析,
 * 在形参或实体内标注 {@link ObsUrl @ObsUrl} 注解, 将会在请求时自动去掉OBS请求前缀, 在响应时自动添加OBS请求前缀
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see ObsUrl @ObsUrl
 * @see ObsUrlAspect
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ConditionalOnWebApplication
public @interface EnableObsUrlConvert {
}
