package com.ysertine.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.ysertine.common.constant.StatusEnum;
import com.ysertine.common.utli.ValueUtils;
import com.ysertine.system.entity.SysRole;
import com.ysertine.system.entity.SysRolePermission;
import com.ysertine.system.entity.SysUserRole;
import com.ysertine.system.service.SysRolePermissionService;
import com.ysertine.system.service.SysRoleService;
import com.ysertine.system.service.SysUserRoleService;

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
	 * 注入系统用户角色Service类
	 */
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 注入系统角色权限Service类
	 */
	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	
	/**
	 * @Title view 
	 * @Description 跳转系统角色列表页面
	 * @author DengJinbo
	 * @date 2019年1月16日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/view")
    public String view() {
        return "sysRole/view";
    }
	
	/**
	 * @Title view 
	 * @Description 系统角色列表
	 * @author DengJinbo
	 * @date 2019年1月16日
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
		String name = ValueUtils.stringValue(request.getParameter("name"), null);
		int status = ValueUtils.intValue(request.getParameter("status"), -99);
		
		SysRole sysRole = new SysRole();
		sysRole.setName(name);
		if (status != -99) {
			sysRole.setStatus(status);
		}
		PageInfo<SysRole> pageInfo = sysRoleService.getPageInfo(pageNum, pageSize, orderBy, sysRole);
		
		resultMap = new HashMap<>();
		resultMap.put("code", 0);
		resultMap.put("count", pageInfo.getTotal());
		resultMap.put("data", pageInfo.getList());
        return resultMap;
    }
	
	/**
	 * @Title add 
	 * @Description 跳转新增页面
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/add")
    public String add() {
        return "sysRole/add";
    }
	
	/**
	 * @Title add 
	 * @Description 新增系统角色
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@Transactional
	@PostMapping(value = "/add")
	public Object add(HttpServletRequest request, Map<String, Object> resultMap) {
		String name = ValueUtils.stringValue(request.getParameter("name"), null);
		String permissionIdStr = request.getParameter("permissionIdStr");
		
		SysRole sysRole = new SysRole();
		sysRole.setName(name);
		sysRole.setStatus(StatusEnum.STATUS_NORMAL.getIndex());
		sysRoleService.saveSelective(sysRole);
		
		if (!StringUtils.isEmpty(permissionIdStr)) {
			String[] permissionIdList = permissionIdStr.split(",");
			SysRolePermission sysRolePermission;
			long sysRoleId = sysRole.getId();
			for (String permissionId : permissionIdList) {
				sysRolePermission = new SysRolePermission();
				sysRolePermission.setRoleId(sysRoleId);
				sysRolePermission.setPermissionId(Long.valueOf(permissionId));
				sysRolePermissionService.saveSelective(sysRolePermission);
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
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @return
	 */
	@GetMapping(value = "/edit")
    public String edit(HttpServletRequest request) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		request.setAttribute("id", id);
        return "sysRole/edit";
    }
	
	/**
	 * @Title getSysRoleInfo 
	 * @Description 根据主键获取系统角色信息
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/getSysRoleInfo")
    public Object getSysRoleInfo(HttpServletRequest request, Map<String, Object> resultMap) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		SysRole sysRole = sysRoleService.getByPrimaryKey(id);
		JSONArray rolePermissionTree = sysRolePermissionService.getRolePermissionTree(sysRole.getId());
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		resultMap.put("data", sysRole);
		resultMap.put("rolePermissionTree", rolePermissionTree);
        return resultMap;
    }
	
	@ResponseBody
	@Transactional
	@PostMapping(value = "/edit")
	public Object edit(HttpServletRequest request, Map<String, Object> resultMap) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		String name = ValueUtils.stringValue(request.getParameter("name"), null);
		String permissionIdStr = request.getParameter("permissionIdStr");
		
		int code = 0;
		String msg = "操作成功";
		SysRole sysRole = sysRoleService.getByPrimaryKey(id);
		if (sysRole != null) {
			sysRole.setName(name);
			sysRoleService.updateByPrimaryKeySelective(sysRole);
			if (!StringUtils.isEmpty(permissionIdStr)) {
				sysRolePermissionService.deleteByRoleId(id);
				String[] permissionIdList = permissionIdStr.split(",");
				SysRolePermission sysRolePermission;
				long sysRoleId = sysRole.getId();
				for (String permissionId : permissionIdList) {
					if (permissionId.equals("on")) {
						continue;
					}
					sysRolePermission = new SysRolePermission();
					sysRolePermission.setRoleId(sysRoleId);
					sysRolePermission.setPermissionId(Long.valueOf(permissionId));
					sysRolePermissionService.saveSelective(sysRolePermission);
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
	 * @Description 根据主键ID列表批量删除系统角色（物理删除）
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/delete")
    public Object delete(HttpServletRequest request, Map<String, Object> resultMap) {
		String ids = ValueUtils.stringValue(request.getParameter("ids"), null);
		
		int code = 0;
		String msg = "操作成功";
		if (!StringUtils.isEmpty(ids)) {
			String[] idList = ids.split(",");
			SysUserRole sysUserRole;
			List<SysUserRole> userRoleList;
			for (String id : idList) {
				sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(Long.valueOf(id));
				userRoleList = sysUserRoleService.listByCriteria(sysUserRole);
				
				if (userRoleList == null || userRoleList.size() < 1) {
					sysRoleService.deleteByPrimaryKey(id);
				} else {
					code = -1;
					msg = "操作失败，已存在系统用户配置了该系统角色，该系统角色不允许删除";
				}
			}
		}
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
		resultMap.put("msg", msg);
        return resultMap;
    }
	
	/**
	 * @Title checkName 
	 * @Description 检索系统角色名称是否存在
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/checkName")
    public Object checkName(HttpServletRequest request, Map<String, Object> resultMap) {
		String name = ValueUtils.stringValue(request.getParameter("name"), null);
		
		SysRole sysRole = new SysRole();
		sysRole.setName(name);
		SysRole getSysRole = sysRoleService.getByCriteria(sysRole);
		
		int code = 0;
		if (getSysRole != null) {
			code = 1;
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
        return resultMap;
    }
}        