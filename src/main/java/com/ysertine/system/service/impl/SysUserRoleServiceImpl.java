package com.ysertine.system.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysUserRole;
import com.ysertine.system.mapper.SysUserRoleMapper;
import com.ysertine.system.service.SysUserRoleService;

/**
 * @Title SysUserRoleServiceImpl.java
 * @Description SysUserRoleService接口实现类
 * @author DengJinbo
 * @date 2019年1月15日
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

	/**
	 * 注入sysUserRoleMapper
	 */
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Cacheable(value = "sysUserRole", key = "#userId")
	@Override
	public Set<String> listRoleNameByUserId(Long userId) {
		return sysUserRoleMapper.listRoleNameByUserId(userId);
	}

}