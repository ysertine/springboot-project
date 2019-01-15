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
	 * @Description 根据系统用户ID查询用户角色名称列表
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 用户角色名称列表
	 */
	Set<String> listRoleNameByUserId(Long userId);

}
