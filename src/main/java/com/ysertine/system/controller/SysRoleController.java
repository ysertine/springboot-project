package com.ysertine.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysertine.common.utli.ValueUtils;
import com.ysertine.system.service.SysRoleService;

/**
 * @Title SysRoleController.java
 * @Description 系统角色控制器
 * @author DengJinbo
 * @date 2019年1月16日
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
	
	/**
	 * 注入系统用户Service类
	 */
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * @Title view 
	 * @Description 跳转系统角色列表页面
	 * @author DengJinbo
	 * @date 2019年1月16日
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/view")
    public String view(HttpServletRequest request) {
        return "sysRole/view";
    }
	
	/**
	 * @Title list 
	 * @Description 获取系统角色列表数据
	 * @author DengJinbo
	 * @date 2019年1月16日
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/list")
    public Object list(HttpServletRequest request) {
		int pageNum = ValueUtils.intValue(request.getParameter("page"), 1);
		int pageSize = ValueUtils.intValue(request.getParameter("limit"), 10);
		String orderBy = ValueUtils.stringValue(request.getParameter("orderBy"), "id desc");
		
		PageInfo<Object> pageInfo = PageHelper.startPage(pageNum, pageSize).setOrderBy(orderBy).doSelectPageInfo(() -> sysRoleService.listAll());
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", 0);
		map.put("count", pageInfo.getTotal());
		map.put("data", sysRoleService.listAll());
        return map;
    }
	
}        