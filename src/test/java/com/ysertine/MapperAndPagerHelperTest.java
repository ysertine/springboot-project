package com.ysertine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysertine.system.entity.SysUser;
import com.ysertine.system.mapper.SysUserMapper;

/**
 * @Title MapperAndPagerHelperTest.java
 * @Description 集成通用Mapper和PagerHelper分页插件测试类
 * @author DengJinbo
 * @date 2018年12月26日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class MapperAndPagerHelperTest {
	
	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(MapperAndPagerHelperTest.class);
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Test
	public void testMapperAndPagerHelper() {
	 	final SysUser user1 = new SysUser("u1", "p1", "s1", "p1");
        final SysUser user2 = new SysUser("u2", "p2", "s2", "p2");
        final SysUser user3 = new SysUser("u3", "p3", "s3", "p3");
        
        sysUserMapper.insertSelective(user1);
        logger.info("[user1回写主键] - [{}]", user1.getId());
        
        sysUserMapper.insertSelective(user2);
        logger.info("[user2回写主键] - [{}]", user2.getId());
        
        sysUserMapper.insertSelective(user3);
        logger.info("[user3回写主键] - [{}]", user3.getId());
        
        final SysUser sysUser = sysUserMapper.selectByUserName("admin");
        logger.info("[调用自己写的SQL] - [{}]", sysUser.getUserName());

        // TODO 模拟分页
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        sysUserMapper.insertSelective(new SysUser("u1", "p1", "s1", "p1"));
        
        // TODO 分页 + 排序 this.sysUserMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.sysUserMapper.selectAll());
        logger.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<SysUser> userPageInfo = new PageInfo<>(this.sysUserMapper.selectAll());
        logger.info("[普通写法] - [{}]", userPageInfo);
	}
}
