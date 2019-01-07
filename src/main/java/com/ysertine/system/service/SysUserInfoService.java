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
	 * @param id
	 * @return
	 */
	SysUser getSysUserByPrimaryKey(Long id);
	
	/**
	 * @Title getSysUserByUserName 
	 * @Description 根据系统用户名查询系统用户
	 * @author DengJinbo
	 * @date 2018年11月27日
	 * @version 1.0
	 * @param userName 系统用户名
	 * @return 用户实体
	 */
	SysUser getSysUserByUserName(String userName);
	
	SysUser getSysUserSelective(SysUser user);
	
	/**
	 * @Title saveSysUserSelective 
	 * @Description 创建系统用户，选择性保存数据
	 * @author DengJinbo
	 * @date 2018年12月27日
	 * @version 1.0
	 * @param sysUser 系统用户实体类
	 * @return
	 */
	SysUser saveSysUserSelective(SysUser sysUser);
	
	/**
	 * @Title deleteSysUserByPrimaryKey 
	 * @Description 根据主键ID删除系统用户
	 * @author DengJinbo
	 * @date 2018年12月27日
	 * @version 1.0
	 * @param id 主键ID
	 */
	void deleteSysUserByPrimaryKey(Long id);

	Set<String> listRoleNameByUserId(Long id);

	Set<String> listResourceUrlByUserId(Long id);
}
