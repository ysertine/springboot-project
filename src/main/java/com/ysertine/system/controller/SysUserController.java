package com.ysertine.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysertine.common.constant.StatusEnum;
import com.ysertine.common.utli.MD5Util;
import com.ysertine.common.utli.ValueUtils;
import com.ysertine.system.entity.SysUser;
import com.ysertine.system.entity.SysUserRole;
import com.ysertine.system.service.SysUserRoleService;
import com.ysertine.system.service.SysUserService;
import com.ysertine.system.util.PasswordHelper;

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
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 密码助手
	 */
	private PasswordHelper passwordHelper = new PasswordHelper();
	
	/**
	 * @Title view 
	 * @Description 跳转管理员列表页面
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/view")
    public String view() {
        return "sysUser/view";
    }
	
	/**
	 * @Title view 
	 * @Description 系统用户列表
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/view")
    public Object view(HttpServletRequest request, Map<String, Object> resultMap) {
		int pageNum = ValueUtils.intValue(request.getParameter("page"), 1);
		int pageSize = ValueUtils.intValue(request.getParameter("limit"), 10);
		String orderBy = ValueUtils.stringValue(request.getParameter("orderBy"), "id desc");
		String username = ValueUtils.stringValue(request.getParameter("username"), null);
		String phone = ValueUtils.stringValue(request.getParameter("phone"), null);
		String email = ValueUtils.stringValue(request.getParameter("email"), null);
		int status = ValueUtils.intValue(request.getParameter("status"), StatusEnum.STATUS_NONEED.getIndex());
		
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser.setPhone(phone);
		sysUser.setEmail(email);
		if (status != StatusEnum.STATUS_NONEED.getIndex()) {
			sysUser.setStatus(status);
		}
		PageInfo<SysUser> pageInfo = sysUserService.getPageInfo(pageNum, pageSize, orderBy, sysUser);
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		resultMap.put("count", pageInfo.getTotal());
		resultMap.put("data", pageInfo.getList());
        return resultMap;
    }
	
	/**
	 * @Title add 
	 * @Description 跳转新增页面
	 * @author DengJinbo
	 * @date 2019年1月17日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/add")
    public String add() {
        return "sysUser/add";
    }
	
	/**
	 * @Title add 
	 * @Description 新增系统用户
	 * @author DengJinbo
	 * @date 2019年1月17日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@Transactional
	@PostMapping(value = "/add")
	public Object add(HttpServletRequest request, Map<String, Object> resultMap) {
		String username = ValueUtils.stringValue(request.getParameter("username"), null);
		String phone = ValueUtils.stringValue(request.getParameter("phone"), null);
		String email = ValueUtils.stringValue(request.getParameter("email"), null);
		String roleIdStr = ValueUtils.stringValue(request.getParameter("roleIdStr"), null);
		
		Date date = new Date();
		String salt = MD5Util.MD5Encode(username + new Date());
		
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser.setPassword("fp666666");
		sysUser.setSalt(salt);
		sysUser.setPhone(phone);
		sysUser.setEmail(email);
		sysUser.setCreateTime(date);
		sysUser.setUpdateTime(date);
		sysUser.setStatus(StatusEnum.STATUS_NORMAL.getIndex());
		passwordHelper.encryptPassword(sysUser);
		sysUserService.saveSelective(sysUser);
		
		if (!StringUtils.isEmpty(roleIdStr)) {
			String[] roleIdList = roleIdStr.split(",");
			
			SysUserRole sysUserRole;
			long sysUserId = sysUser.getId();
			for (String roleId : roleIdList) {
				sysUserRole = new SysUserRole();
				sysUserRole.setUserId(sysUserId);
				sysUserRole.setRoleId(Long.valueOf(roleId));
				sysUserRoleService.save(sysUserRole);
			}
		}
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		resultMap.put("msg", "创建成功");
        return resultMap;
	}
	
	/**
	 * @Title edit 
	 * @Description 跳转修改页面
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param request 请求参数集
	 * @return
	 */
	@GetMapping(value = "/edit")
    public String edit(HttpServletRequest request) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		request.setAttribute("id", id);
        return "sysUser/edit";
    }

	/**
	 * @Title getSysUserInfo 
	 * @Description 根据系统用户Id获取用户信息
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/getSysUserInfo")
    public Object getSysUserInfo(HttpServletRequest request, Map<String, Object> resultMap) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		SysUser sysUser = sysUserService.getByPrimaryKey(id);
		Set<Long> userRoleIdList = sysUserRoleService.listRoleIdByUserId(id);
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		resultMap.put("data", sysUser);
		resultMap.put("userRoleIdList", userRoleIdList);
        return resultMap;
    }
	
	/**
	 * @Title edit 
	 * @Description 修改系统用户
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@Transactional
	@PostMapping(value = "/edit")
	public Object edit(HttpServletRequest request, Map<String, Object> resultMap) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		String username = ValueUtils.stringValue(request.getParameter("username"), null);
		String phone = ValueUtils.stringValue(request.getParameter("phone"), null);
		String email = ValueUtils.stringValue(request.getParameter("email"), null);
		String roleIdStr = ValueUtils.stringValue(request.getParameter("roleIdStr"), null);
		
		int code = 0;
		String msg = "操作成功";
		SysUser sysUser = sysUserService.getByPrimaryKey(id);
		if (sysUser != null) {
			sysUser.setUsername(username);
			sysUser.setPhone(phone);
			sysUser.setEmail(email);
			sysUser.setUpdateTime(new Date());
			sysUserService.updateByPrimaryKeySelective(sysUser);
			
			if (!StringUtils.isEmpty(roleIdStr)) {
				sysUserRoleService.deleteByUserId(id);
				String[] roleIdList = roleIdStr.split(",");
				SysUserRole sysUserRole;
				for (String roleId : roleIdList) {
					sysUserRole = new SysUserRole();
					sysUserRole.setUserId(id);
					sysUserRole.setRoleId(Long.valueOf(roleId));
					sysUserRoleService.save(sysUserRole);
				}
			}
		} else {
			code = -1;
			msg = "要修改的数据不存在";
		}
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
		resultMap.put("msg", msg);
        return resultMap;
	}
	
	/**
	 * @Title delete 
	 * @Description 根据主键ID列表批量删除用户（状态删除）
	 * @author DengJinbo
	 * @date 2019年1月19日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/delete")
    public Object delete(HttpServletRequest request, Map<String, Object> resultMap) {
		String ids = ValueUtils.stringValue(request.getParameter("ids"), null);
		int status = StatusEnum.STATUS_DELETE.getIndex();
		
		if (!StringUtils.isEmpty(ids)) {
			String[] idList = ids.split(",");
			SysUser sysUser;
			for (String id : idList) {
				sysUser = sysUserService.getByPrimaryKey(Long.valueOf(id));
				sysUser.setStatus(status);
				sysUserService.updateByPrimaryKeySelective(sysUser);
			}
		}
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		resultMap.put("msg", "操作成功");
        return resultMap;
    }
	
	/***
	 * @Title checkUsername 
	 * @Description 检索用户名是否存在
	 * @author DengJinbo
	 * @date 2019年1月18日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return code（0=不存在，1=已存在）
	 */
	@ResponseBody
	@GetMapping(value = "/checkUsername")
    public Object checkUsername(HttpServletRequest request, Map<String, Object> resultMap) {
		String username = ValueUtils.stringValue(request.getParameter("username"), null);
		
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		SysUser getSysUser = sysUserService.getByCriteria(sysUser);
		
		int code = 0;
		if (getSysUser != null) {
			code = 1;
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
        return resultMap;
    }
	
	/***
	 * @Title checkPhone 
	 * @Description 检索手机号码是否存在
	 * @author DengJinbo
	 * @date 2019年1月18日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return code（0=不存在，1=已存在）
	 */
	@ResponseBody
	@GetMapping(value = "/checkPhone")
    public Object checkPhone(HttpServletRequest request, Map<String, Object> resultMap) {
		String phone = ValueUtils.stringValue(request.getParameter("phone"), null);
		
		SysUser sysUser = new SysUser();
		sysUser.setPhone(phone);
		SysUser getSysUser = sysUserService.getByCriteria(sysUser);
		
		int code = 0;
		if (getSysUser != null) {
			code = 1;
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
        return resultMap;
    }
	
	/***
	 * @Title checkEamil 
	 * @Description 检索电子邮箱是否存在
	 * @author DengJinbo
	 * @date 2019年1月18日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return code（0=不存在，1=已存在）
	 */
	@ResponseBody
	@GetMapping(value = "/checkEamil")
    public Object checkEamil(HttpServletRequest request, Map<String, Object> resultMap) {
		String email = ValueUtils.stringValue(request.getParameter("email"), null);
		
		SysUser sysUser = new SysUser();
		sysUser.setEmail(email);
		SysUser getSysUser = sysUserService.getByCriteria(sysUser);
		
		int code = 0;
		if (getSysUser != null) {
			code = 1;
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
        return resultMap;
    }
}        