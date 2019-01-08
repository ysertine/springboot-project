package com.ysertine.system.mapper;

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
