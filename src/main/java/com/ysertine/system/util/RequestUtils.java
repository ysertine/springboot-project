package com.ysertine.system.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.ysertine.system.entity.SysUser;

/**
 * @Title RequestUtils.java
 * @Description 
 * @author DengJinbo
 * @date 2019年1月8日
 */
public class RequestUtils {

	/**
	 * @Title currentLoginUser 
	 * @Description 获取当前登录的用户，若用户未登录，则返回未登录 json
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @return
	 */
    public static SysUser currentLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Object principal = subject.getPrincipals().getPrimaryPrincipal();
            if (principal instanceof SysUser) {
                return (SysUser) principal;
            }
        }
        return null;
    }
}
