package pro.haichuang.framework.sdk.aliyunoss.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import pro.haichuang.framework.sdk.aliyunoss.config.aspect.OssUrlAspect;

import java.lang.annotation.*;

/**
 * 启用阿里云OSS地址解析
 *
 * <p>该注解应标注在 {@code Controller} 层方法上, 代表该请求启用OSS地址解析,
 * 在形参或实体内标注 {@link OssUrl @OssUrl} 注解, 将会在请求时自动去掉OSS请求前缀, 在响应时自动添加OSS请求前缀
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see OssUrl @OssUrl
 * @see OssUrlAspect
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@ConditionalOnWebApplication
public @interface EnableOssUrlConvert {
}
