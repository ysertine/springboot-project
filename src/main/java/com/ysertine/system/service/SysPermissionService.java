package com.ysertine.system.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ysertine.common.service.BaseService;
import com.ysertine.system.entity.SysPermission;

/**
 * @Title SysPermissionService.java
 * @Description 系统资源Service层接口
 * @author DengJinbo
 * @date 2019年1月21日
 */
public interface SysPermissionService extends BaseService<SysPermission> {

	/**
	 * @Title listByParentId 
	 * @Description 根据父类ID获取系统资源列表
	 * @author DengJinbo
	 * @date 2019年1月22日
	 * @version 1.0
	 * @param parentId
	 * @return
	 */
	List<SysPermission> listByParentId(long parentId);

	/**
	 * @Title getPermissionTree 
	 * @Description 获取系统资源树的数据
	 * @author DengJinbo
	 * @date 2019年1月22日
	 * @version 1.0
	 * @return
	 */
	JSONArray getPermissionTree();
}
