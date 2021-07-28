package pro.haichuang.framework.base.config.mvc;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson自动配置
 *
 * <p>该类自动配置了 {@code Jackson} 的常用功能
 * 已默认配置的如下:
 * <ul>
 *     <li>{@link BigInteger} 自动序列化为 {@link String} 字符串</li>
 *     <li>{@link Long} 自动序列化为 {@link String} 字符串</li>
 *     <li>{@link javax.xml.crypto.Data} 自动序列化为 {@code yyyy-MM-dd HH:mm:ss} 字符串</li>
 *     <li>{@link LocalDateTime} 自动序列化为 {@code yyyy-MM-dd HH:mm:ss} 字符串</li>
 *     <li>{@link LocalDate} 自动序列化为 {@code yyyy-MM-dd} 字符串</li>
 *     <li>{@link LocalTime} 自动序列化为 {@code HH:mm:ss} 字符串</li>
 * </ul>
 * <p>该类采用 {@link Jackson2ObjectMapperBuilderCustomizer} 的原因是默认 {@code Jackson} 已存在一些默认配置内容,
 * 通过此种方式可以实现在不修改默认配置的情况下插入我们自定义的配置
 *
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 * @see WebMvcConfig
 */
@Configuration(proxyBeanMethods = false)
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, new ToStringSerializer());
            jacksonObjectMapperBuilder.serializerByType(Long.class, new ToStringSerializer());

            jacksonObjectMapperBuilder.serializerByType(LocalTime.class,
                    new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
            jacksonObjectMapperBuilder.serializerByType(LocalDate.class,
                    new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER));
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class,
                    new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));

            jacksonObjectMapperBuilder.deserializerByType(LocalTime.class,
                    new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
            jacksonObjectMapperBuilder.deserializerByType(LocalDate.class,
                    new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class,
                    new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER));
        };
    }

    /**
     * LocalDateTime自定义转换器
     *
     * @author JiYinchuan
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String s) {
            return LocalDateTime.parse(s, DatePattern.NORM_DATETIME_FORMATTER);
        }
    }

    /**
     * LocalDate自定义转换器
     *
     * @author JiYinchuan
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class LocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String s) {
            return LocalDate.parse(s, DatePattern.NORM_DATE_FORMATTER);
        }
    }

    /**
     * LocalTime自定义转换器
     *
     * @author JiYinchuan
     * @version 1.0.0
     * @since 1.0.0
     */
    public static class LocalTimeConverter implements Converter<String, LocalTime> {

        @Override
        public LocalTime convert(String s) {
            return LocalTime.parse(s, DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
        }
    }
}
