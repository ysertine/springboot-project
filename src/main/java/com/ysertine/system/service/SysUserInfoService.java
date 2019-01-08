package com.ysertine.system.service;

import java.util.Set;

import com.ysertine.system.entity.SysUser;

/**
 * @Title SysUserInfoService.java
 * @Description 系统用户信息Service层接口
 * @author DengJinbo
 * @date 2018年11月27日
 */
public interface SysUserInfoService {

	/**
	 * @Title getSysUserByPrimaryKey 
	 * @Description 根据主键ID查询系统用户
	 * @author DengJinbo
	 * @date 2018年12月27日
	 * @version 1.0
	 * @param id 系统用户ID
	 * @return 系统用户实体
	 */
	SysUser getSysUserByPrimaryKey(Long id);
	
	/**
	 * @Title getSysUserByUserName 
	 * @Description 根据系统用户名查询系统用户
	 * @author DengJinbo
	 * @date 2018年11月27日
	 * @version 1.0
	 * @param userName 系统用户名
	 * @return 系统用户实体
	 */
	SysUser getSysUserByUserName(String userName);
	
	/**
	 * @Title getSysUserByUserNameAndPassword 
	 * @Description 根据传入的参数查询系统用户
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userName 用户名
	 * @param password 密码
	 * @return 系统用户实体
	 */
	SysUser getSysUserByUserNameAndPassword(String userName, String password);
	
	/**
	 * @Title saveSysUserSelective 
	 * @Description 创建系统用户，选择性保存数据
	 * @author DengJinbo
	 * @date 2018年12月27日
	 * @version 1.0
	 * @param sysUser 系统用户实体类
	 * @return 系统用户实体
	 */
	SysUser saveSysUserSelective(SysUser sysUser);
	
	/**
	 * @Title deleteSysUserByPrimaryKey 
	 * @Description 根据主键ID删除系统用户
	 * @author DengJinbo
	 * @date 2018年12月27日
	 * @version 1.0
	 * @param id 系统用户ID
	 */
	void deleteSysUserByPrimaryKey(Long id);

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
