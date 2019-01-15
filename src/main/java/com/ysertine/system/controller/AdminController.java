package com.ysertine.system.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ysertine.common.utli.R;
import com.ysertine.system.entity.SysUser;

/**
 * @Title AdminController.java
 * @Description 后台系统用户注册/登录控制器
 * @author DengJinbo
 * @date 2019年1月8日
 */
@Controller
public class AdminController {
	
	/**
	 * @Title login 
	 * @Description 跳转登录页面
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/login")
    public String login() {
        return "login";
    }
	
	/**
	 * @Title login 
	 * @Description 系统用户登录
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param request
	 * @param sysUser
	 * @param model
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/login")
    public R login(HttpServletRequest request, SysUser sysUser, Model model) {
		if (StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())) {
            return R.error("用户名或密码不能为空！");
        }
		
		/**
		 * 用户输入的账号和密码，存到UsernamePasswordToken对象中，然后由shiro内部认证对比，
		 * 认证执行者交由 com.battcn.config.AuthRealm 中 doGetAuthenticationInfo 处理
		 * 当以上认证成功后会向下执行，认证失败会抛出异常
		 */
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟 shiro 的拦截地址内．不然后会报空指针
        Subject subject = SecurityUtils.getSubject();
        try {
        	subject.login(token);
        } catch (ExcessiveAttemptsException e) {
            token.clear();
            return R.error("账号登录失败次数过多，请稍后再试！");
        }  catch (DisabledAccountException e) {
        	token.clear();
            return R.error("账户已被禁用！");
        } catch (AuthenticationException e) {
        	token.clear();
            return R.error("用户名或密码错误！");
        }
        // 执行到这里说明用户已登录成功
        return R.ok();
	}
	
	/**
	 * @Title index 
	 * @Description 登录成功跳转首页
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public ModelAndView index() {
		SysUser currentLoginUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		ModelAndView view = new ModelAndView();
		view.addObject("currentLoginUser", currentLoginUser);
		return view;
	}
	
	/**
	 * @Title main 
	 * @Description 首页默认页面
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/main")
	public String main() {
		return "main";
	}
}        