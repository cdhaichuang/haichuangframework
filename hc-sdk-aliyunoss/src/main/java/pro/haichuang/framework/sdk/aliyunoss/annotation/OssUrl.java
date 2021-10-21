package pro.haichuang.framework.sdk.aliyunoss.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import pro.haichuang.framework.sdk.aliyunoss.config.aspect.OssUrlAspect;

import java.lang.annotation.*;

/**
 * 解析阿里云Oss资源
 *
 * <p>将该类标注在实体字段上或 {@code Controller} 层方法形参上, 将在请求时自动去掉OSS请求前缀, 在响应时自动加上OSS请求前缀
 *
 * @author JiYinchuan
 * @see EnableOssUrlConvert @EnableOssUrlConvert
 * @see OssUrlAspect
 * @since 1.1.0.211021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@ConditionalOnWebApplication
public @interface OssUrl {
}
