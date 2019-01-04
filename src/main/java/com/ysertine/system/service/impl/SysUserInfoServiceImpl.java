package com.ysertine.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ysertine.system.entity.SysUser;
import com.ysertine.system.mapper.SysUserMapper;
import com.ysertine.system.service.SysUserInfoService;

/**
 * @Title SysUserInfoServiceImpl.java
 * @Description SysUserInfoService接口实现类
 * @author DengJinbo
 * @date 2018年11月27日
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(SysUserInfoServiceImpl.class);

	/**
	 * 注入sysUserMapper
	 */
	@Autowired
	private SysUserMapper sysUserMapper;

	@Cacheable(value = "sysUser", key = "#id")
    @Override
	public SysUser getByPrimaryKey(Long id) {
		logger.info("进入 getByPrimaryKey 方法");
		return sysUserMapper.selectByPrimaryKey(id);
	}
	
	@Cacheable(value = "sysUser")
	@Override
	public SysUser getByUserName(String userName) {
		logger.info("进入 getByUserName 方法");
		return sysUserMapper.selectByUserName(userName);
	}

	@CachePut(value = "sysUser", key = "#sysUser.id")
	@Override
	public SysUser saveSelective(SysUser sysUser) {
		logger.info("进入 saveSelective 方法");
		sysUserMapper.insertSelective(sysUser);
		return sysUser;
	}

	@CacheEvict(value = "sysUser", key = "#id")
	@Override
	public void deleteByPrimaryKey(Long id) {
		logger.info("进入 deleteByPrimaryKey 方法");
		sysUserMapper.deleteByPrimaryKey(id);
	}
}