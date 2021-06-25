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
 * @author JiYinchuan
 * @version 1.0
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
