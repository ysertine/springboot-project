package com.ysertine;

import static org.junit.Assert.assertEquals;

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
 * @Title RedisCacheTest.java
 * @Description 整合Lettuce Redis测试类
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
	private RedisTemplate<String, Object> customRedisTemplate;

	@Test
	public void testRedisCache() {
		// 测试线程安全
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		IntStream.range(0, 1000) .forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("key", 1)));

		stringRedisTemplate.opsForValue().set("key1", "v5霸气");
		assertEquals(stringRedisTemplate.opsForValue().get("key1"), "v5霸气");

		String key = "ysertine:user:1";
		customRedisTemplate.opsForValue().set(key, new SysUser("大波波", "123456", "ssssss1", "15988888888"));

		final SysUser sysUser = (SysUser) customRedisTemplate.opsForValue().get(key);
		assertEquals(sysUser.getUserName(), "大波波");
		logger.info("==> 当你看到这条信息，表明测试验证通过了！");
	}
}
