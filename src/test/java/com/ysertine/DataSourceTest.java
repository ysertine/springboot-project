package com.ysertine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ysertine.common.controller.CustomPropertiesController;
import com.ysertine.system.entity.SysUser;
import com.ysertine.system.mapper.SysUserMapper;

/**
 * @Title DataSourceTest.java
 * @Description 集成多数据源测试类
 * @author DengJinbo
 * @date 2018年12月25日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class DataSourceTest {

	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustomPropertiesController.class);

	@Autowired
	private SysUserMapper sysUserMapper;

	@Test
	public void testDynamicDatasource() {
		SysUser sysUser;
		for (int i = 1; i <= 2; i++) {
			// i为奇数时调用selectById方法获取，i为偶数时调用selectByUserName方法获取
			sysUser = i % 2 == 1 ? sysUserMapper.selectByPrimaryKey(1L) : sysUserMapper.selectByUsername("admin");
			logger.info("{}-->={}", sysUser.getId(), sysUser.getUsername());
		}
	}
}
