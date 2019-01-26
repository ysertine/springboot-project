package com.ysertine.system.service;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
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
	 * @Description 根据系统用户ID查询系统用户系统资源URL列表
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 系统用户配置的系统资源URL列表
	 */
	Set<String> listResourceUrlByUserId(Long userId);
	
	/**
	 * @Title listMenuIdByRoleId 
	 * @Description 根据系统角色ID查询该角色配置的菜单权限ID列表
	 * @author DengJinbo
	 * @date 2019年1月23日
	 * @version 1.0
	 * @param roleId 系统角色ID
	 * @return 系统角色配置的菜单资源ID列表
	 */
	List<Long> listMenuIdByRoleId(Long roleId);
	
	/**
	 * @Title listButtonIdByRoleId 
	 * @Description 根据系统角色ID查询该角色配置的按钮权限ID列表
	 * @author DengJinbo
	 * @date 2019年1月26日
	 * @version 1.0
	 * @param roleId 系统角色ID
	 * @return 系统角色配置的按钮资源ID列表
	 */
	List<Long> listButtonIdByRoleId(Long roleId);

	/**
	 * @Title getRolePermissionTree 
	 * @Description 获取系统角色权限树
	 * @author DengJinbo
	 * @date 2019年1月26日
	 * @version 1.0
	 * @param roleId 系统角色ID
	 * @return 系统角色配置的资源数
	 */
	JSONArray getRolePermissionTree(Long roleId);

	/**
	 * @Title deleteByRoleId 
	 * @Description 根据系统角色ID删除该角色配置的系统资源列表
	 * @author DengJinbo
	 * @date 2019年1月26日
	 * @version 1.0
	 * @param roleId 系统角色ID
	 */
	void deleteByRoleId(Long roleId);

}
