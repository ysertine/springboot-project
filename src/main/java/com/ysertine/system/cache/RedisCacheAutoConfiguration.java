package com.ysertine.system.cache;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Title RedisCacheAutoConfiguration.java
 * @Description 自定义Template 默认情况下的模板只能支持RedisTemplate<String, String>，也就是只能存入字符串，
 * 		所以自定义模板是很有必要的，当自定义了模板又想使用String存储这时候就可以使用StringRedisTemplate的方式，它们并不冲突…
 * @author DengJinbo
 * @date 2018年12月27日
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

	/**
	 * @Title redisCacheTemplate 
	 * @Description 自定义Template
	 * @author DengJinbo
	 * @date 2018年12月27日
	 * @version 1.0
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Serializable> customRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
