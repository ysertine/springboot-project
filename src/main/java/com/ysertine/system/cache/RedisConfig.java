package com.ysertine.system.cache;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
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
import com.ysertine.system.util.MD5Util;

/**
 * @Title RedisConfig.java
 * @Description 自定义Template 存储序列化对象
 *  	默认的StringRedisTemplate模板只能支持RedisTemplate<String, String>，也就是只能存入字符串。
 *  	需要加上 @EnableCaching，spring-data-cache相关注解才会生效。
 * @author DengJinbo
 * @date 2018年12月27日
 */
@EnableCaching
@Configuration
@AutoConfigureAfter(RedisConfig.class)
@ConfigurationProperties(prefix = "spring.cache.redis")
public class RedisConfig extends CachingConfigurerSupport {

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
	 * 缓存失效时间，在配置文件中设置
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
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

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
	
	/**
	 * @Description 重写默认的key生成器，在使用 @Cacheable 时，如果不指定key，则使用这个默认的key生成器生成的key
	 * @author DengJinbo
	 * @date 2019年1月4日
	 * @version 1.0
	 * @return
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator() {
			/**
			 * 对参数进行拼接后MD5
			 */
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(".").append(method.getName());

				StringBuilder paramsSb = new StringBuilder();
				for (Object param : params) {
					// 如果不指定，默认生成包含到键值中
					if (param != null) {
						paramsSb.append(param.toString());
					}
				}
				if (paramsSb.length() > 0) {
					sb.append("_").append(paramsSb);
				}
				String key = MD5Util.MD5Encode(sb.toString(), "");
				return key;
			}

		};
	}
}