package com.ysertine.system.service;

import java.util.Set;

import com.ysertine.common.service.BaseService;
import com.ysertine.system.entity.SysRolePermission;

/**
 * @Title SysRolePermissionService.java
 * @Description 系统角色权限Service层接口
 * @author DengJinbo
 * @date 2019年1月15日
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {

	/**
	 * @Title listResourceUrlByUserId 
	 * @Description 根据系统用户ID查询用户系统权限资源URL列表
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 用户系统权限资源URL列表
	 */
	Set<String> listResourceUrlByUserId(Long userId);
}
