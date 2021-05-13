package pro.haichuang.framework.base.config.mvc;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.haichuang.framework.base.enums.BaseEnum;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * FastJsonConfig
 *
 * @author JiYinchuan
 * @version 1.0
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

    public static class DateSerializer implements ObjectSerializer {

        public static final DateSerializer INSTANCE = new DateSerializer();

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
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

    public static class EnumSerializer implements ObjectSerializer {

        public static final DateSerializer INSTANCE = new DateSerializer();

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
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
