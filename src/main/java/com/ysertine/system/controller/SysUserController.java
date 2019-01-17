package com.ysertine.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysertine.common.utli.ValueUtils;
import com.ysertine.system.entity.SysUser;
import com.ysertine.system.service.SysUserService;

/**
 * @Title SysUserController.java
 * @Description 管理员控制器
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
	 * @Description 跳转管理员列表页面
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/view")
    public String view(HttpServletRequest request) {
        return "sysUser/view";
    }
	
	/**
	 * @Title view 
	 * @Description 系统用户列表
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/list")
    public Object list(HttpServletRequest request) {
		int pageNum = ValueUtils.intValue(request.getParameter("page"), 1);
		int pageSize = ValueUtils.intValue(request.getParameter("limit"), 10);
		String orderBy = ValueUtils.stringValue(request.getParameter("orderBy"), "id desc");
		String username = ValueUtils.stringValue(request.getParameter("username"), null);
		String phone = ValueUtils.stringValue(request.getParameter("phone"), null);
		String email = ValueUtils.stringValue(request.getParameter("email"), null);
		int status = ValueUtils.intValue(request.getParameter("status"), -99);
		
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser.setPhone(phone);
		sysUser.setEmail(email);
		if (status != -99) {
			sysUser.setStatus(status);
		}
		PageInfo<SysUser> pageInfo = sysUserService.getPageInfo(pageNum, pageSize, orderBy, sysUser);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 0);
		map.put("count", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
        return map;
    }
	
	/**
	 * @Title add 
	 * @Description 跳转新增页面
	 * @author DengJinbo
	 * @date 2019年1月17日
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/add")
    public String add(HttpServletRequest request) {
        return "sysUser/add";
    }
	
	/**
	 * @Title addSysUser 
	 * @Description 
	 * @author DengJinbo
	 * @date 2019年1月17日
	 * @version 1.0
	 * @return
	 */
	@PostMapping(value = "/addSysUser")
	public Object addSysUser(SysUser sysUser) {
		sysUserService.saveSelective(sysUser);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 0);
		map.put("msg", "创建成功！");
        return map;
	}
}        