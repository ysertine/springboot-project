package com.ysertine.system.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.ysertine.system.entity.SysUser;

/**
 * @Title PasswordHelper.java
 * @Description 密码助手
 * @author DengJinbo
 * @date 2019年1月14日
 */
public class PasswordHelper {

	/**
	 * 算法名称
	 */
	private String algorithmName = "md5";
	
	/**
	 * 散列次数
	 */
	private int hashIterations = 2;

	/**
	 * @Title encryptPassword 
	 * @Description 加密密码
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param sysUser
	 */
	public void encryptPassword(SysUser sysUser) {
		String newPassword = new SimpleHash(algorithmName, 
				sysUser.getPassword(),
				ByteSource.Util.bytes(sysUser.getSalt()), 
				hashIterations).toHex();
		sysUser.setPassword(newPassword);

	}

	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		SysUser sysUser = new SysUser();
		sysUser.setUsername("admin");
		sysUser.setPassword("admin");
		passwordHelper.encryptPassword(sysUser);
		System.out.println(sysUser.toString());
	}
}
