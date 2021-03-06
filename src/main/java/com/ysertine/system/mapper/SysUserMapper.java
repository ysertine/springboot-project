package com.ysertine.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ysertine.system.entity.SysUser;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Title SysUserMapper.java
 * @Description 系统用户Mapper 
 * 		通过 @TargetDataSource("slave") 注解可以切换数据库，
 * 		由于我们的动态数据源配置了默认库，所以如果mapper方法是操作默认库的可以不需要注解
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * @Title selectByUsername
	 * @Description 根据系统用户名查询系统用户，从slave数据源中获取用户数据。
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @param username 系统用户名
	 * @return 系统用户实体
	 */
	SysUser selectByUsername(String username);

}
