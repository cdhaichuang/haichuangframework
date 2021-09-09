package pro.haichuang.framework.base.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import pro.haichuang.framework.base.config.mvc.enums.EnumConverterFactory;

import java.lang.annotation.*;

/**
 * 枚举值转换注解
 *
 * <p>在枚举类中静态方法上标注此注解时, 会通过 {@link EnumConverterFactory} 进行 {@code Convert} 转换, 主要用于请求参数反序列化等场景
 * <p>注意: 此注解必须在枚举类方法中标注, 标注的方法必须为静态方法, 形参限制为一个 {@link String} 类型, 返回值为当前枚举对象
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see EnumConverterFactory
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@ConditionalOnWebApplication
public @interface EnumConvertValue {
}
