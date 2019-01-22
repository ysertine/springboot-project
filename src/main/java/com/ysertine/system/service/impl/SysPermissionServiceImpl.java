package com.ysertine.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysPermission;
import com.ysertine.system.mapper.SysPermissionMapper;
import com.ysertine.system.service.SysPermissionService;

/**
 * @Title SysPermissionServiceImpl.java
 * @Description SysPermissionService接口实现类
 * @author DengJinbo
 * @date 2019年1月21日
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {

	/**
	 * 注入sysPermissionMapper
	 */
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Cacheable(value = "listSysPermission", key = "#parentId")
	@Override
	public List<SysPermission> listByParentId(long parentId) {
		SysPermission sysPermission = new SysPermission();
		sysPermission.setParentId(parentId);
		return sysPermissionMapper.select(sysPermission);
	}

	@Cacheable(value = "permissionTree")
	@Override
	public JSONArray getPermissionTree() {
		List<SysPermission> menuList = this.listByParentId(0);
		JSONArray menuArray = new JSONArray();
		JSONObject menuObject;
		JSONArray buttonArray;
		JSONObject buttonObject;
		List<SysPermission> buttonList;
		for (SysPermission menu : menuList) {
			buttonArray = new JSONArray();
			buttonList = this.listByParentId(menu.getId());
			for (SysPermission button : buttonList) {
				buttonObject = new JSONObject();
				buttonObject.put("title", button.getName());
				buttonObject.put("value", button.getId());
				buttonObject.put("data", new JSONArray());
				buttonArray.add(buttonObject);
			}
			menuObject = new JSONObject();
			menuObject.put("title", menu.getName());
			menuObject.put("value", menu.getId());
			menuObject.put("data", buttonArray);
			menuArray.add(menuObject);
		}
		return menuArray;
	}

}