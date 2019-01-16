package com.ysertine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ysertine.system.entity.SysUser;
import com.ysertine.system.service.SysUserService;

/**
 * @Title SpringCacheTest.java
 * @Description 使用Spring Cache集成Redis测试类
 * @author DengJinbo
 * @date 2018年12月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class SpringCacheTest {

	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(SpringCacheTest.class);

	/**
	 * 注入系统用户Service类
	 */
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 注入自定义的redis模板
	 */
	@Autowired
	private RedisTemplate<String, Object> customRedisTemplate;

	@Test
	public void testSpringCache() {
		
		SysUser sysUser = new SysUser("五花牛", "123456", "salt", "15911111111");
		sysUserService.saveSelective(sysUser);
		customRedisTemplate.opsForValue().set("sysUser::" + sysUser.getId(), sysUser);
		logger.info("[saveSelective] - [{}]", sysUser.toString());
		
		SysUser sysUser1 = sysUserService.getByPrimaryKey(sysUser.getId());
		SysUser sysUser2 = (SysUser) customRedisTemplate.opsForValue().get("sysUser::" + sysUser.getId());
		assertEquals(sysUser1.getUsername(), sysUser2.getUsername());
		
		final SysUser sysUser3 = sysUserService.getByUsername(sysUser2.getUsername());
		logger.info("[getByUserName] - [{}]", sysUser3.getUsername());
		
		sysUserService.deleteByPrimaryKey(sysUser3.getId());
	}
}
