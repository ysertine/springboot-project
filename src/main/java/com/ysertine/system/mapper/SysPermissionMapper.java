package com.ysertine.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ysertine.system.entity.SysPermission;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Title SysPermissionMapper.java
 * @Description 系统权限Mapper
 * 		通过 @TargetDataSource("slave") 注解可以切换数据库，
 * 		由于我们的动态数据源配置了默认库，所以如果mapper方法是操作默认库的可以不需要注解
 * @author DengJinbo
 * @date 2019年1月7日
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
	
	/**
	 * @Title selectByParentId 
	 * @Description 根据父类资源ID查询子类资源列表
	 * @author DengJinbo
	 * @date 2019年1月26日
	 * @version 1.0
	 * @param parentId 父类资源ID
	 * @return 子类资源列表
	 */
	List<SysPermission> selectByParentId(Long parentId);

}
