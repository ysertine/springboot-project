package com.ysertine.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysPermission;
import com.ysertine.system.mapper.SysPermissionMapper;
import com.ysertine.system.service.SysPermissionService;

/**
 * @Title SysPermissionServiceImpl.java
 * @Description SysPermissionService接口实现类
 * @author DengJinbo
 * @date 2019年1月21日
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {

	/**
	 * 注入sysPermissionMapper
	 */
	@SuppressWarnings("unused")
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	
}