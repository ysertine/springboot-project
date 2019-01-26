package com.ysertine.system.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.ysertine.system.entity.SysUserRole;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Title SysUserRoleMapper.java
 * @Description 系统用户角色Mapper 
 * 		通过 @TargetDataSource("slave") 注解可以切换数据库，
 * 		由于我们的动态数据源配置了默认库，所以如果mapper方法是操作默认库的可以不需要注解
 * @author DengJinbo
 * @date 2019年1月7日
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	/**
	 * @Title selectRoleNameByUserId 
	 * @Description 根据系统用户ID查询系统用户角色名称列表
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 系统用户角色名称列表
	 */
	Set<String> selectRoleNameByUserId(Long userId);

	/**
	 * @Title selectRoleIdByUserId 
	 * @Description 根据系统用户ID查询系统用户角色ID列表
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 系统用户角色ID列表
	 */
	Set<Long> selectRoleIdByUserId(Long userId);

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
