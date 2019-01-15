package com.ysertine.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ysertine.common.utli.ValueUtils;
import com.ysertine.system.entity.SysUser;
import com.ysertine.system.service.SysUserService;

/**
 * @Title SysUserController.java
 * @Description 系统用户控制器
 * @author DengJinbo
 * @date 2019年1月15日
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	/**
	 * 注入系统用户Service类
	 */
	@Autowired
	private SysUserService sysUserService;
	
	
	/**
	 * @Title view 
	 * @Description 系统用户列表
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/view")
    public ModelAndView view(HttpServletRequest request) {
		int pageNum = ValueUtils.intValue(request.getParameter("pageNum"), 1);
		int pageSize = ValueUtils.intValue(request.getParameter("pageSize"), 10);
		String orderBy = ValueUtils.stringValue(request.getParameter("orderBy"), "id desc");
		
		PageInfo<SysUser> pageInfo = sysUserService.getPageInfo(pageNum, pageSize, orderBy);
		
		ModelAndView view = new ModelAndView();
		view.addObject("pageInfo", pageInfo);
        return view;
    }
	
}        