package pro.haichuang.framework.base.config.mvc.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;
import pro.haichuang.framework.base.annotation.EnumConvertValue;
import pro.haichuang.framework.base.config.mvc.WebMvcConfig;
import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.exception.EnumIllegalArgumentException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求枚举转换工厂
 *
 * <p>该类为处理请求参数中的枚举对应值和枚举对象进行相互转换而存在
 * <hr>
 * <p>当请求参数为枚举时, 且枚举中存在标注了 {@link EnumConvertValue @EnumConvertValue} 注解的静态方法或者类实现了 {@link BaseEnum} 的枚举
 * (两者同时存在时标注了 {@code @EnumConvertValue} 注解的静态方法优先级为最高), 可以自动将该对象属性名称与请求参数进行匹配,
 * <p>Warning: 底层通过 {@link ConverterFactory} 进行实现时必须指定请求参数数据类型与转换的数据类型,
 * 因此当请求参数为枚举时, 请求参数的类型必须为 {@link String},
 * 转换的枚举必须在方法上标注 {@code @EnumConvertValue} 注解或者实现 {@link BaseEnum} 接口
 * <p>{@code @EnumConvertValue} 注解使用方法请参见该注解类文档
 * <p>推荐自定义枚举类实现 {@link BaseEnum} 接口而不是使用 {@code @EnumConvertValue} 注解
 * <hr>
 * <p>当请求参数为请求体时需要在枚举类中加上下列代码实现, 代码示例:
 * <pre>
 *     &#064;JsonCreator(mode = JsonCreator.Mode.DELEGATING)
 *     public static [枚举类] resolve(String value) {
 *         return BaseEnum.resolve(value, [枚举类].class);
 *     }
 * </pre>
 * <hr>
 *
 * @author JiYinchuan
 * @see EnumConvertValue @EnumConvertValue
 * @see BaseEnum
 * @see WebMvcConfig
 * @since 1.1.0.211021
 */
public class EnumConverterFactory implements ConverterFactory<String, Enum<?>> {

    private static final Map<Class<? extends Enum<?>>, EnumConverterHolder> CONVERTER_MAP = new ConcurrentHashMap<>();

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends Enum<?>> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        EnumConverterHolder holder = CONVERTER_MAP.computeIfAbsent(targetType, EnumConverterHolder::create);
        return (Converter<String, T>) (holder.annotationConverter != null ? holder.annotationConverter : holder.baseEnumConverter);
    }

    static class EnumConverterHolder {

        private final StringToAnnotationEnumConverter<?> annotationConverter;
        private final StringToBaseEnumConverter<?> baseEnumConverter;

        public EnumConverterHolder(StringToAnnotationEnumConverter<?> converter, StringToBaseEnumConverter<?> baseEnumConverter) {
            this.annotationConverter = converter;
            this.baseEnumConverter = baseEnumConverter;
        }

        @SuppressWarnings("unchecked")
        private static EnumConverterHolder create(Class<?> targetType) {
            final List<Method> enumConvertValueMethods = new ArrayList<>();
            final Method[] allDeclaredMethods = targetType.getDeclaredMethods();
            for (Method declaredMethod : allDeclaredMethods) {
                if (declaredMethod.getAnnotation(EnumConvertValue.class) != null) {
                    enumConvertValueMethods.add(declaredMethod);
                }
            }
            if (enumConvertValueMethods.isEmpty()) {
                if (BaseEnum.class.isAssignableFrom(targetType)) {
                    return instance((Class<BaseEnum>) targetType);
                } else {
                    return new EnumConverterHolder(null, null);
                }
            }
            if (enumConvertValueMethods.size() != 1) {
                throw new EnumIllegalArgumentException("@EnumConvertValue can only be marked on one method");
            }
            Method enumConvertValueMethod = enumConvertValueMethods.get(0);
            boolean isStatic = Modifier.isStatic(enumConvertValueMethod.getModifiers());
            if (!isStatic) {
                throw new EnumIllegalArgumentException("@EnumConvertValue can only be marked on static method");
            }
            return new EnumConverterHolder(new StringToAnnotationEnumConverter<>(enumConvertValueMethod), null);
        }

        private static <T extends BaseEnum> EnumConverterHolder instance(Class<T> targetType) {
            return new EnumConverterHolder(null, new StringToBaseEnumConverter<>(targetType));
        }
    }

    static class StringToBaseEnumConverter<T extends BaseEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        StringToBaseEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(@NonNull String source) {
            return BaseEnum.resolve(source, enumType);
        }
    }

    static class StringToAnnotationEnumConverter<T extends Enum<?>> implements Converter<String, T> {

        private final Method method;

        StringToAnnotationEnumConverter(Method method) {
            this.method = method;
            this.method.setAccessible(true);
        }

        @Override
        @SuppressWarnings("unchecked")
        public T convert(@NonNull String source) {
            if (source.isEmpty()) {
                return null;
            }
            try {
                return (T) method.invoke(null, source);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
