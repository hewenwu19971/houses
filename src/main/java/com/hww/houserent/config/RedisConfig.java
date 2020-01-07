package com.hww.houserent.config;

import com.hww.houserent.entity.Housing_InformationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<Object, Object> Housing_InformationEntityredisTemplate(){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Housing_InformationEntity> serializer = new Jackson2JsonRedisSerializer<Housing_InformationEntity>(Housing_InformationEntity.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

}