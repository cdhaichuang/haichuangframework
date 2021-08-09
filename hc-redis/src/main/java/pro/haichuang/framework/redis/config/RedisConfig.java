package pro.haichuang.framework.redis.config;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pro.haichuang.framework.redis.service.DefaultRedisServiceImpl;
import pro.haichuang.framework.redis.service.RedisService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * RedisConfig
 *
 * <p>该类为 [Redis] 核心配置, 对 {@code spring-boot-starter-cache} 组件进行了集成, 同时优化了 {@link RedisTemplate} 的序列化与反序列化规则
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @see pro.haichuang.framework.redis.config.autoconfiguration.RedisAutoConfiguration
 * @since 1.0.0
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> target.getClass().getName() + ":" + method.getName() + ":" +
                JSONObject.toJSONString(params).replaceAll(":", ";");
    }

    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("CACHE#")
                .entryTtl(Duration.ofSeconds(120))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(configuration).build();
    }

    @Bean
    public <V> RedisTemplate<String, V> redisTemplate() {
        RedisTemplate<String, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());

        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());

        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(RedisService.class)
    public RedisService redisService() {
        return new DefaultRedisServiceImpl(redisTemplate());
    }

    /**
     * RedisKey序列化规则
     *
     * @return RedisSerializer
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * RedisValue序列化规则
     *
     * @return RedisSerializer
     */
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer(new ObjectMapper()
                .activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
                        ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
                .registerModule(new JavaTimeModule()
                        .addSerializer(LocalDate.class,
                                new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)))
                        .addSerializer(LocalDateTime.class,
                                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)))
                        .addDeserializer(LocalDate.class,
                                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)))
                        .addDeserializer(LocalDateTime.class,
                                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN))))
        );
    }
}
