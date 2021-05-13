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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson配置
 *
 * @author JiYinchuan
 * @version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.class, new ToStringSerializer());

            jacksonObjectMapperBuilder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
            jacksonObjectMapperBuilder.serializerByType(LocalDate.class, new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER));
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));

            jacksonObjectMapperBuilder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
            jacksonObjectMapperBuilder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER));
        };
    }

    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String s) {
            return LocalDateTime.parse(s, DatePattern.NORM_DATETIME_FORMATTER);
        }
    }

    public static class LocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String s) {
            return LocalDate.parse(s, DatePattern.NORM_DATE_FORMATTER);
        }
    }

    public static class LocalTimeConverter implements Converter<String, LocalTime> {

        @Override
        public LocalTime convert(String s) {
            return LocalTime.parse(s, DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
        }
    }
}
