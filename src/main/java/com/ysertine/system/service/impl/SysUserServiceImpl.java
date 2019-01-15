package com.ysertine.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysUser;
import com.ysertine.system.mapper.SysUserMapper;
import com.ysertine.system.service.SysUserService;

/**
 * @Title SysUserInfoServiceImpl.java
 * @Description SysUserInfoService接口实现类
 * @author DengJinbo
 * @date 2018年11月27日
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

	/**
	 * 注入sysUserMapper
	 */
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Override
	public SysUser getByUsername(String username) {
		return sysUserMapper.selectByUsername(username);
	}
	
}