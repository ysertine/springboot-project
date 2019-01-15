package com.ysertine.system.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysRolePermission;
import com.ysertine.system.mapper.SysRolePermissionMapper;
import com.ysertine.system.service.SysRolePermissionService;

/**
 * @Title SysRolePermissionServiceImpl.java
 * @Description SysRolePermissionService接口实现类
 * @author DengJinbo
 * @date 2019年1月15日
 */
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements SysRolePermissionService {
	
	/**
	 * 注入sysRolePermissionMapper
	 */
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	@Cacheable(value = "sysUser", key = "#userId")
	@Override
	public Set<String> listResourceUrlByUserId(Long userId) {
		return sysRolePermissionMapper.listResourceUrlByUserId(userId);
	}

}
