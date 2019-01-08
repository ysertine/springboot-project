package com.ysertine.system.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.ysertine.system.entity.SysUser;
import com.ysertine.system.service.SysUserInfoService;

/**
 * @Title MyShiroRealm.java
 * @Description 
 * @author DengJinbo
 * @date 2019年1月5日
 */
public class MyShiroRealm extends AuthorizingRealm {

	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	/**
	 * shiro初始化比较早，里面用到的bean都会在cache初始化之前被初始化，需要为shiro使用到的bean加 @Lazy
	 */
	@Lazy
	@Autowired
	private SysUserInfoService sysUserInfoService;

	/**
	 * 获取登录用户的授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
		Object principal = principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		if (principal instanceof SysUser) {
			SysUser loginUser = (SysUser) principal;
			Set<String> roleNameList = sysUserInfoService.listRoleNameByUserId(loginUser.getId());
			authorizationInfo.addRoles(roleNameList);

			Set<String> ResourceUrlList = sysUserInfoService.listResourceUrlByUserId(loginUser.getId());
			authorizationInfo.addStringPermissions(ResourceUrlList);
		}
		logger.info("---- 获取到以下权限 ----");
		logger.info(authorizationInfo.getStringPermissions().toString());
		logger.info("---------------- Shiro 权限获取成功 ----------------------");
		return authorizationInfo;
	}

	/**
	 * 获取登录用户的身份验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String userName = usernamePasswordToken.getUsername();
		String password = String.valueOf(usernamePasswordToken.getPassword());
		
		// 从数据库获取对应用户名密码的用户
		SysUser sysUser = sysUserInfoService.getSysUserByUserNameAndPassword(userName, password);
		if (sysUser != null) {
			// 用户为禁用状态
			if (sysUser.getStatus() != 1) {
				throw new DisabledAccountException();
			}
			logger.info("---------------- Shiro 凭证认证成功 ----------------------");
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, // 用户
					sysUser.getPassword(), // 密码
					getName() // realm name
			);
			return authenticationInfo;
		}
		throw new UnknownAccountException();
	}

}
