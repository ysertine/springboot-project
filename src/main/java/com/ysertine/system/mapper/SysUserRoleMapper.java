package com.ysertine.system.mapper;

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

}
