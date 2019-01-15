package com.ysertine.system.service;

import com.ysertine.common.service.BaseService;
import com.ysertine.system.entity.SysUser;

/**
 * @Title SysUserService.java
 * @Description 系统用户Service层接口
 * @author DengJinbo
 * @date 2018年11月27日
 */
public interface SysUserService extends BaseService<SysUser> {

	/**
	 * @Title getSysUserByUsername 
	 * @Description 根据系统用户名查询系统用户
	 * @author DengJinbo
	 * @date 2018年11月27日
	 * @version 1.0
	 * @param username 系统用户名
	 * @return 系统用户实体
	 */
	SysUser getByUsername(String username);
	
}
