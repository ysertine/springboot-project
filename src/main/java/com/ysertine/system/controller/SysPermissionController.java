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

import com.github.pagehelper.PageInfo;
import com.ysertine.common.constant.StatusEnum;
import com.ysertine.common.utli.ValueUtils;
import com.ysertine.system.entity.SysPermission;
import com.ysertine.system.entity.SysRolePermission;
import com.ysertine.system.service.SysPermissionService;
import com.ysertine.system.service.SysRolePermissionService;

/**
 * @Title SysPermissionController.java
 * @Description 系统资源控制器
 * @author DengJinbo
 * @date 2019年1月15日
 */
@Controller
@RequestMapping("/sysPermission")
public class SysPermissionController {
	
	/**
	 * 注入系统资源Service类
	 */
	@Autowired
	private SysPermissionService sysPermissionService;
	
	/**
	 * 注入系统角色权限Service类
	 */
	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	
	/**
	 * @Title view 
	 * @Description 跳转系统资源列表页面
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @return
	 */
	@GetMapping(value = "/view")
    public String view() {
        return "sysPermission/view";
    }
	
	/**
	 * @Title view 
	 * @Description 系统资源列表
	 * @author DengJinbo
	 * @date 2019年1月21日
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
		String orderBy = ValueUtils.stringValue(request.getParameter("orderBy"), "id asc");
		String name = ValueUtils.stringValue(request.getParameter("name"), null);
		String resourceUrl = ValueUtils.stringValue(request.getParameter("resourceUrl"), null);
		int resourceType = ValueUtils.intValue(request.getParameter("resourceType"), -99);
		long parentId = ValueUtils.longValue(request.getParameter("parentId"), -99);
		int status = ValueUtils.intValue(request.getParameter("status"), StatusEnum.STATUS_NONEED.getIndex());
		
		SysPermission sysPermission = new SysPermission();
		sysPermission.setName(name);
		sysPermission.setResourceUrl(resourceUrl);
		if (resourceType != -99) {
			sysPermission.setResourceType(resourceType);
		}
		if (parentId != -99) {
			sysPermission.setParentId(parentId);
		}
		if (status != StatusEnum.STATUS_NONEED.getIndex()) {
			sysPermission.setStatus(status);
		}
		PageInfo<SysPermission> pageInfo = sysPermissionService.getPageInfo(pageNum, pageSize, orderBy, sysPermission);
		
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
	 * @param request 请求参数集
	 * @return
	 */
	@GetMapping(value = "/add")
    public String add(HttpServletRequest request) {
		long parentId = ValueUtils.longValue(request.getParameter("parentId"), 0); // 0=菜单，2=按钮
		request.setAttribute("parentId", parentId);
        return "sysPermission/add";
    }
	
	/**
	 * @Title add 
	 * @Description 新增系统资源
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
		String resourceUrl = ValueUtils.stringValue(request.getParameter("resourceUrl"), null);
		long parentId = ValueUtils.longValue(request.getParameter("parentId"), 0); // 1=菜单，2=按钮
		
		SysPermission sysPermission = new SysPermission();
		sysPermission.setName(name);
		sysPermission.setPermission(resourceUrl.replace("/", ":"));
		sysPermission.setResourceUrl(resourceUrl);
		sysPermission.setResourceType(parentId == 0 ? 1 : 2);
		sysPermission.setParentId(parentId);
		sysPermission.setStatus(StatusEnum.STATUS_NORMAL.getIndex());
		sysPermissionService.saveSelective(sysPermission);
		
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
        return "sysPermission/edit";
    }
	
	/**
	 * @Title getSysPermissionInfo 
	 * @Description 根据主键获取系统资源信息
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/getSysPermissionInfo")
    public Object getSysPermissionInfo(HttpServletRequest request, Map<String, Object> resultMap) {
		long id = ValueUtils.longValue(request.getParameter("id"), 0);
		SysPermission sysPermission = sysPermissionService.getByPrimaryKey(id);
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		resultMap.put("data", sysPermission);
        return resultMap;
    }
	
	/**
	 * @Title edit 
	 * @Description 根据主键修改系统资源信息
	 * @author DengJinbo
	 * @date 2019年1月21日
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
		String name = ValueUtils.stringValue(request.getParameter("name"), null);
		String resourceUrl = ValueUtils.stringValue(request.getParameter("resourceUrl"), null);
		
		int code = 0;
		String msg = "操作成功";
		SysPermission sysPermission = sysPermissionService.getByPrimaryKey(id);
		if (sysPermission != null) {
			sysPermission.setName(name);
			sysPermission.setResourceUrl(resourceUrl);
			sysPermissionService.updateByPrimaryKeySelective(sysPermission);
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
	 * @Description 根据主键ID列表批量删除系统资源（物理删除）
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
			SysRolePermission sysRolePermission;
			List<SysRolePermission> rolePermissionList;
			for (String id : idList) {
				sysRolePermission = new SysRolePermission();
				sysRolePermission.setPermissionId(Long.valueOf(id));
				rolePermissionList = sysRolePermissionService.listByCriteria(sysRolePermission);
				if (rolePermissionList == null || rolePermissionList.size() < 1) {
					sysPermissionService.deleteByPrimaryKey(id);
				} else {
					code = -1;
					msg = "操作失败，已存在系统角色配置了该系统资源，该系统资源不允许删除";
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
	 * @Description 检索系统资源名称是否存在
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
		
		SysPermission sysPermission = new SysPermission();
		sysPermission.setName(name);
		SysPermission getSysPermission = sysPermissionService.getByCriteria(sysPermission);
		
		int code = 0;
		if (getSysPermission != null) {
			code = 1;
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
        return resultMap;
    }
	
	/**
	 * @Title checkPermissionName 
	 * @Description 检索系统资源链接是否存在
	 * @author DengJinbo
	 * @date 2019年1月21日
	 * @version 1.0
	 * @param request 请求参数集
	 * @param resultMap 返回结果集
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/checkResourceUrl")
    public Object checkResourceUrl(HttpServletRequest request, Map<String, Object> resultMap) {
		String resourceUrl = ValueUtils.stringValue(request.getParameter("resourceUrl"), null);
		
		SysPermission sysPermission = new SysPermission();
		sysPermission.setResourceUrl(resourceUrl);
		SysPermission getSysPermission = sysPermissionService.getByCriteria(sysPermission);
		
		int code = 0;
		if (getSysPermission != null) {
			code = 1;
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
        return resultMap;
    }
}        