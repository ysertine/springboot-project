package com.ysertine.system.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysertine.common.utli.RequestUtils;
import com.ysertine.common.utli.StringUtils;
import com.ysertine.system.entity.SysUser;

/**
 * @Title AdminController.java
 * @Description 后台系统用户注册/登录控制器
 * @author DengJinbo
 * @date 2019年1月8日
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	/**
	 * @Title loginPage 
	 * @Description 跳转登录页面
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/login")
    public String loginPage() {
        return "admin/login";
    }
	
	/**
	 * @Title login 
	 * @Description 系统用户登录
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param userName 系统用户名
	 * @param password 密码
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/login")
    public String login(String userName, String password, HttpServletRequest request) {
		
		/**
		 * 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
		 * 认证执行者交由 com.battcn.config.AuthRealm 中 doGetAuthenticationInfo 处理
		 * 当以上认证成功后会向下执行,认证失败会抛出异常
		 */
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟 shiro 的拦截地址内．不然后会报空指针
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        } catch (ExcessiveAttemptsException e) {
            token.clear();
            request.setAttribute("msg", "账号登录失败次数过多，请稍后再试");
            return "admin/login";
        }  catch (DisabledAccountException e) {
        	token.clear();
            request.setAttribute("msg", "账户已被禁用");
            return "admin/login";
        } catch (AuthenticationException e) {
        	token.clear();
            request.setAttribute("msg", "用户名或密码错误");
            return "admin/login";
        }
        // 执行到这里说明用户已登录成功
        return "redirect:/admin/index";
	}
	
	/**
	 * @Title loginSuccessMessage 
	 * @Description 登录成功跳转
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/index")
    public String loginSuccessMessage(HttpServletRequest request) {
        String userName = "未登录";
        SysUser currentLoginUser = RequestUtils.currentLoginUser();

        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser.getUserName())) {
            userName = currentLoginUser.getUserName();
        } else {
            return "redirect:/admin/login";
        }
        request.setAttribute("userName", userName);
        return "admin/index";
    }
}        