package com.ysertine.system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ysertine.common.service.impl.BaseServiceImpl;
import com.ysertine.system.entity.SysPermission;
import com.ysertine.system.entity.SysRolePermission;
import com.ysertine.system.mapper.SysPermissionMapper;
import com.ysertine.system.mapper.SysRolePermissionMapper;
import com.ysertine.system.service.SysRolePermissionService;

/**
 * @Title SysRolePermissionServiceImpl.java
 * @Description SysRolePermissionService接口实现类
 * @author DengJinbo
 * @date 2019年1月15日
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements SysRolePermissionService {
	
	/**
	 * 注入sysRolePermissionMapper
	 */
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	/**
	 * 注入SysPermissionMapper
	 */
	@Autowired
	private SysPermissionMapper SysPermissionMapper;

	@Cacheable(value = "sysUserPermission", key = "#userId")
	@Override
	public Set<String> listResourceUrlByUserId(Long userId) {
		return sysRolePermissionMapper.selectResourceUrlByUserId(userId);
	}

	@Cacheable(value = "sysRoleMenuPermission", key = "#roleId")
	@Override
	public List<Long> listMenuIdByRoleId(Long roleId) {
		return sysRolePermissionMapper.selectMenuIdByRoleId(roleId);
	}
	
	@Cacheable(value = "sysRoleButtonPermission", key = "#roleId")
	@Override
	public List<Long> listButtonIdByRoleId(Long roleId) {
		return sysRolePermissionMapper.selectButtonIdByRoleId(roleId);
	}

	@Cacheable(value = "sysRolePermissionTree", key = "#roleId")
	@Override
	public JSONArray getRolePermissionTree(Long roleId) {
		List<SysPermission> menuList = SysPermissionMapper.selectByParentId(0L);
		JSONArray menuArray = new JSONArray();
		JSONObject menuObject;
		JSONArray buttonArray;
		JSONObject buttonObject;
		List<SysPermission> buttonList;
		
		List<Long> roleMenuIdList = this.listMenuIdByRoleId(roleId);
		List<Long> roleButtonIdList = this.listButtonIdByRoleId(roleId);
		
		for (SysPermission menu : menuList) {
			buttonArray = new JSONArray();
			buttonList = SysPermissionMapper.selectByParentId(menu.getId());
			for (SysPermission button : buttonList) {
				buttonObject = new JSONObject();
				buttonObject.put("title", button.getName());
				buttonObject.put("value", button.getId());
				buttonObject.put("data", new JSONArray());
				for (Long roleButtonId : roleButtonIdList) {
					if (roleButtonId == button.getId()) {
						buttonObject.put("checked", true);
					}
				}
				buttonArray.add(buttonObject);
			}
			menuObject = new JSONObject();
			menuObject.put("title", menu.getName());
			menuObject.put("value", menu.getId());
			menuObject.put("data", buttonArray);
			for (Long roleMenuId : roleMenuIdList) {
				if (roleMenuId == menu.getId()) {
					menuObject.put("checked", true);
				}
			}
			menuArray.add(menuObject);
		}
		return menuArray;
	}

	@CacheEvict(value = "sysRolePermissionTree", key = "#roleId")
	@Override
	public void deleteByRoleId(Long roleId) {
		sysRolePermissionMapper.deleteByRoleId(roleId);
	}
}
