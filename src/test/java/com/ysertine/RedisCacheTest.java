package com.ysertine;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ysertine.system.entity.SysUser;

/**
 * @Title MapperAndPagerHelperTest.java
 * @Description 集成通用Mapper和PagerHelper分页插件测试类
 * @author DengJinbo
 * @date 2018年12月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class RedisCacheTest {

	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheTest.class);

	/**
	 * 注入默认的redis模板
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 注入自定义的redis模板
	 */
	@Autowired
	private RedisTemplate<String, Serializable> customRedisTemplate;

	@Test
	public void testRedisCache() {
		// TODO 测试线程安全
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		IntStream.range(0, 1000) .forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1)));

		stringRedisTemplate.opsForValue().set("k1", "v1");
		final String k1 = stringRedisTemplate.opsForValue().get("k1");
		logger.info("[字符缓存结果] - [{}]", k1);

		// TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
		String key = "ysertine:user:1";
		customRedisTemplate.opsForValue().set(key, new SysUser("大波波", "123456", "ssssss1", "15988888888"));

		// TODO 对应 String（字符串）
		final SysUser sysUser = (SysUser) customRedisTemplate.opsForValue().get(key);
		logger.info("[对象缓存结果] - [{}]", sysUser);
	}
}
