package com.ysertine.system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements SysRolePermissionService {
	
	/**
	 * 注入sysRolePermissionMapper
	 */
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;

	@Cacheable(value = "sysUserPermission", key = "#userId")
	@Override
	public Set<String> listResourceUrlByUserId(Long userId) {
		return sysRolePermissionMapper.listResourceUrlByUserId(userId);
	}

	@Override
	public List<Long> listMenuIdByRoleId(Long roleId) {
		return sysRolePermissionMapper.listMenuIdByRoleId(roleId);
	}

}
