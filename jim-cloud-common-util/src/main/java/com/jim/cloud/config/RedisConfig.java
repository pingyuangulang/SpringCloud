package com.jim.cloud.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 *
 * @author jim
 * @date 2019/6/15 12:36
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    /**
     * 定义RedisTemplate的Bean实例
     * @param factory
     * @return
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, String> createRedisTemplateBean(RedisConnectionFactory factory) {

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);

        // 设置json序列化格式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // String序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key-value的key使用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);

        // key-value的value使用Json的序列化方式
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        // key-value(hash -->hashKey-hashValue)的hashKey使用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // key-value(hash -->hashKey-hashValue)的hashValue使用Json的序列化方式
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
