package pro.haichuang.framework.base.config.mvc;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * FastJson默认配置
 *
 * <p>该类默认配置了 {@code FastJson} 的常用功能, 如需自定义配置需要手动注入 {@link FastJsonConfig}</p>
 * 已默认配置的如下:
 * <ul>
 *     <li>{@link BigInteger} 自动序列化为 {@link String} 字符串</li>
 *     <li>{@link Long} 自动序列化为 {@link String} 字符串</li>
 *     <li>{@link javax.xml.crypto.Data} 自动序列化为 {@code yyyy-MM-dd HH:mm:ss} 字符串</li>
 *     <li>{@link LocalDateTime} 自动序列化为 {@code yyyy-MM-dd HH:mm:ss} 字符串</li>
 *     <li>{@link LocalDate} 自动序列化为 {@code yyyy-MM-dd} 字符串</li>
 *     <li>{@link LocalTime} 自动序列化为 {@code HH:mm:ss} 字符串</li>
 *     <li>{@link BaseEnum} 下枚举 自动序列化为 {@link BaseEnum#value()} 字符串</li>
 *     <li>{@link Enum} 自动序列化为 {@link String} 字符串</li>
 *     <li>{@link java.util.List} 为空时自动序列化为 {@code []} 空集合</li>
 * </ul>
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see WebMvcConfig
 */
@Configuration(proxyBeanMethods = false)
public class FastJsonConfig {

    @Bean
    @ConditionalOnMissingBean(com.alibaba.fastjson.support.config.FastJsonConfig.class)
    public com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig() {
        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig = new com.alibaba.fastjson.support.config.FastJsonConfig();
        SerializeConfig globalInstance = SerializeConfig.globalInstance;

        globalInstance.put(BigInteger.class, ToStringSerializer.instance);
        globalInstance.put(Long.class, ToStringSerializer.instance);
        globalInstance.put(Long.TYPE, ToStringSerializer.instance);
        globalInstance.put(LocalDateTime.class, DateSerializer.INSTANCE);
        globalInstance.put(LocalDate.class, DateSerializer.INSTANCE);
        globalInstance.put(LocalTime.class, DateSerializer.INSTANCE);
        globalInstance.put(BaseEnum.class, EnumSerializer.INSTANCE);

        fastJsonConfig.setSerializeConfig(globalInstance);
        fastJsonConfig.setSerializerFeatures(
                // 用枚举toString()值输出
                SerializerFeature.WriteEnumUsingToString,
                // List为null时输出[]
                SerializerFeature.WriteNullListAsEmpty
        );
        JSON.DEFAULT_GENERATE_FEATURE = JSON.DEFAULT_GENERATE_FEATURE
                | SerializerFeature.WriteEnumUsingToString.getMask()
                | SerializerFeature.WriteNullListAsEmpty.getMask();
        fastJsonConfig.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        return fastJsonConfig;
    }

    /**
     * 时间类型序列化内部类
     *
     * @author JiYinchuan
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class DateSerializer implements ObjectSerializer {

        public static final DateSerializer INSTANCE = new DateSerializer();

        @Override
        public void write(JSONSerializer serializer, @Nullable Object object, Object fieldName, Type fieldType, int features) {
            SerializeWriter out = serializer.out;
            if (object == null) {
                out.writeNull();
                return;
            }
            Temporal val = (Temporal) object;
            if (val instanceof LocalDateTime) {
                out.writeString(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN).format(val));
            }else if (val instanceof LocalDate) {
                out.writeString(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN).format(val));
            }else if (val instanceof LocalTime) {
                out.writeString(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN).format(val));
            }
        }
    }

    /**
     * 枚举类型序列化内部类
     *
     * @author JiYinchuan
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class EnumSerializer implements ObjectSerializer {

        public static final DateSerializer INSTANCE = new DateSerializer();

        @Override
        public void write(JSONSerializer serializer, @Nullable Object object, Object fieldName, Type fieldType, int features) {
            SerializeWriter out = serializer.out;
            if (object == null) {
                out.writeNull();
                return;
            }
            BaseEnum val = (BaseEnum) object;
            out.writeString(val.value());
        }
    }
}
