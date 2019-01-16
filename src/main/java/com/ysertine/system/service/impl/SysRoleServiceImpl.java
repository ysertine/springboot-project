package com.ysertine.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysRole;
import com.ysertine.system.mapper.SysRoleMapper;
import com.ysertine.system.service.SysRoleService;

/**
 * @Title SysRoleServiceImpl.java
 * @Description SysRoleService接口实现类
 * @author DengJinbo
 * @date 2019年1月16日
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

	/**
	 * 注入sysRoleMapper
	 */
	@SuppressWarnings("unused")
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
}