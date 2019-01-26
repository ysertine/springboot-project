package com.ysertine.system.service;

import java.util.Set;

import com.ysertine.common.service.BaseService;
import com.ysertine.system.entity.SysUserRole;

/**
 * @Title SysUserRoleService.java
 * @Description 系统用户角色Service层接口
 * @author DengJinbo
 * @date 2019年1月15日
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

	/**
	 * @Title listRoleNameByUserId 
	 * @Description 根据系统用户ID查询系统用户角色名称列表
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 系统用户角色名称列表
	 */
	Set<String> listRoleNameByUserId(Long userId);

	/**
	 * @Title listRoleIdByUserId 
	 * @Description 根据系统用户ID查询系统用户角色ID列表
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 系统用户角色ID列表
	 */
	Set<Long> listRoleIdByUserId(Long userId);

	/**
	 * @Title deleteByUserId 
	 * @Description 根据系统用户ID删除该系统用户角色列表
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param userId 系统用户ID
	 */
	void deleteByUserId(Long userId);

}
