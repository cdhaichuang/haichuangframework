package pro.haichuang.framework.base.config.mvc.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求枚举转换工厂
 *
 * <p>该类为处理 {@code Jackson} 请求参数中的枚举对应值和枚举对象进行相互转换而存在
 * <p>对于请求参数接收类型为实现了 {@link BaseEnum} 的对象, 可以自动将该对象属性名称与请求参数进行匹配,
 * 请求参数值值使用 {@link BaseEnum#value()} 的值自动转换为对应的枚举对象,
 * 通过 {@link ConverterFactory} 进行实现时必须指定请求参数数据类型与转换的数据类型,
 * 因此需要用到请求参数自定转换为枚举时必须保证请求参数值类型为 {@code String} , 转换的枚举必须实现 {@link BaseEnum} 接口
 * <p>对于请求体自动解析需要在枚举对象中加上下列代码实现, 代码示例:
 * <pre>
 *     &#064;JsonCreator(mode = JsonCreator.Mode.DELEGATING)
 *     public static [枚举类名] resolve(String value) {
 *         return BaseEnum.resolve(value, [枚举类名].class);
 *     }
 * </pre>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see pro.haichuang.framework.base.config.mvc.WebMvcConfig
 * @see BaseEnum
 */
public class ParamEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class<? extends BaseEnum>, Converter<String, ?>> CONVERTER_MAP = new HashMap<>();

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends BaseEnum> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        Converter<String, T> converter = (Converter<String, T>) CONVERTER_MAP.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            CONVERTER_MAP.put(targetType, converter);
        }
        return converter;
    }

    static class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

        private final Map<String, T> enumMap = new HashMap<>();
        private final Class<T> enumType;

        StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(String.valueOf(e.value()), e);
            }
        }

        @Override
        public T convert(@NonNull String source) {
            return BaseEnum.resolve(source, enumType);
        }
    }
}
