package com.ysertine.system.mapper;

import com.ysertine.system.annotation.TargetDataSource;
import com.ysertine.system.entity.SysUser;

/**
 * @Title SysUserMapper.java
 * @Description 系统用户Mapper
 * @author DengJinbo
 * @date 2018年12月25日
 */
public interface SysUserMapper {

	/**
	 * @Title selectById
	 * @Description 根据ID查询实体类，从master数据源中获取用户数据
	 *  	由于我们的动态数据源配置了默认库，所以如果mapper方法是操作默认库的可以不需要注解
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @param id 系统用户ID
	 * @return
	 */
	SysUser selectById(Long id);

	/**
	 * @Title selectByUsername
	 * @Description 根据系统用户名查询系统用户，从slave数据源中获取用户数据
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @param userName 系统用户名
	 * @return 系统用户实体
	 */
	@TargetDataSource("slave")
	SysUser selectByUserName(String userName);

}
