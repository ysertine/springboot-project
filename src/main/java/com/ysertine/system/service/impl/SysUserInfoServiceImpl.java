package com.ysertine.system.service.impl;

import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ysertine.system.entity.SysUser;
import com.ysertine.system.mapper.SysRolePermissionMapper;
import com.ysertine.system.mapper.SysUserMapper;
import com.ysertine.system.mapper.SysUserRoleMapper;
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
	 * 注入sysUserMapper
	 */
	@Autowired
	private SysUserMapper sysUserMapper;
	
	/**
	 * 注入sysUserRoleMapper
	 */
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	/**
	 * 注入sysRolePermissionMapper
	 */
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	

	@Cacheable(value = "sysUser", key = "#id")
    @Override
	public SysUser getSysUserByPrimaryKey(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
	
	@Cacheable(value = "sysUser")
	@Override
	public SysUser getSysUserByUsername(String username) {
		return sysUserMapper.selectByUsername(username);
	}
	
	@Cacheable(value = "sysUser")
	@Override
	public SysUser getSysUserByUsernameAndPassword(String username, String password) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("username", username);
		parameterMap.put("password", password);
		return sysUserMapper.selectByUsernameAndPassword(parameterMap);
	}

	@CachePut(value = "sysUser", key = "#sysUser.id")
	@Override
	public SysUser saveSysUserSelective(SysUser sysUser) {
		sysUserMapper.insertSelective(sysUser);
		return sysUser;
	}

	@CacheEvict(value = "sysUser", key = "#id")
	@Override
	public void deleteSysUserByPrimaryKey(Long id) {
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Cacheable(value = "sysUser", key = "#userId")
	@Override
	public Set<String> listRoleNameByUserId(Long userId) {
		return sysUserRoleMapper.listRoleNameByUserId(userId);
	}

	@Cacheable(value = "sysUser", key = "#userId")
	@Override
	public Set<String> listResourceUrlByUserId(Long userId) {
		return sysRolePermissionMapper.listResourceUrlByUserId(userId);
	}
}