package com.ysertine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ysertine.system.entity.SysUser;
import com.ysertine.system.service.SysUserInfoService;

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
	 * 注入系统用户信息Service类
	 */
	@Autowired
	private SysUserInfoService sysUserInfoService;


	@Test
	public void testSpringCacheTest() {
		
		final SysUser sysUser = sysUserInfoService.saveSelective(new SysUser("牛牛", "123456", "salt", "15911111111"));
		logger.info("[saveSelective] - [{}]", sysUser);
		
		SysUser sysUser1 = sysUserInfoService.getByPrimaryKey(sysUser.getId());
		
		final SysUser sysUser2 = sysUserInfoService.getByUserName(sysUser1.getUserName());
		logger.info("[getByUserName] - [{}]", sysUser2.toString());
		
		sysUserInfoService.deleteByPrimaryKey(sysUser2.getId());
	}
}
