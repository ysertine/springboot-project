package com.ysertine.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.ysertine.system.entity.SysRolePermission;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Title SysRolePermissionMapper.java
 * @Description 系统角色权限Mapper
 * 		通过 @TargetDataSource("slave") 注解可以切换数据库，
 * 		由于我们的动态数据源配置了默认库，所以如果mapper方法是操作默认库的可以不需要注解
 * @author DengJinbo
 * @date 2019年1月7日
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

	/**
	 * @Title selectResourceUrlByUserId 
	 * @Description 根据系统用户ID查询用户系统权限资源URL列表
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userId 系统用户ID
	 * @return 用户系统权限资源URL列表
	 */
	Set<String> selectResourceUrlByUserId(Long userId);

	/**
	 * @Title selectMenuIdByRoleId 
	 * @Description 根据系统角色ID查询该角色配置的菜单权限ID列表
	 * @author DengJinbo
	 * @date 2019年1月23日
	 * @version 1.0
	 * @param roleId 系统角色ID
	 * @return 系统角色配置的菜单权限ID列表
	 */
	List<Long> selectMenuIdByRoleId(Long roleId);

	/**
	 * @Title selectButtonIdByRoleId 
	 * @Description 根据系统角色ID查询该角色配置的按钮权限ID列表
	 * @author DengJinbo
	 * @date 2019年1月26日
	 * @version 1.0
	 * @param roleId 系统角色ID
	 * @return 系统角色配置的按钮资源ID列表
	 */
	List<Long> selectButtonIdByRoleId(Long roleId);

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
