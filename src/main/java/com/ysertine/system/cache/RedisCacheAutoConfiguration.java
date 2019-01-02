package com.ysertine.system.cache;

import java.io.Serializable;
import java.time.Duration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Title RedisCacheAutoConfiguration.java
 * @Description 自定义Template 默认情况下的模板只能支持RedisTemplate<String,
 *              String>，也就是只能存入字符串，
 *              所以自定义模板是很有必要的，当自定义了模板又想使用String存储这时候就可以使用StringRedisTemplate的方式，它们并不冲突…
 * @author DengJinbo
 * @date 2018年12月27日
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@ConfigurationProperties(prefix = "spring.cache.redis")
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

	/**
	 * 设置缓存失效时间
	 */
	private Duration timeToLive = Duration.ZERO;

	public void setTimeToLive(Duration timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @Title cacheManager 
	 * @Description 配置缓存管理
	 * @author DengJinbo
	 * @date 2018年12月29日
	 * @version 1.0
	 * @param factory
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		// 解决查询缓存转换异常的问题
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		// 配置序列化（解决乱码的问题
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(timeToLive)
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.disableCachingNullValues();

		RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(config).build();
		return cacheManager;
	}
}
