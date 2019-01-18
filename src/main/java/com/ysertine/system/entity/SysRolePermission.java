package com.ysertine.system.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ysertine.common.entity.BaseEntity;

/**
 * @Title SysRolePermission.java
 * @Description 系统角色权限实体类
 * @author DengJinbo
 * @date 2019年1月7日
 */
@Table(name = "sys_role_permission")
@SuppressWarnings("serial")
public class SysRolePermission extends BaseEntity {

	/**
	 * 主键ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 系统角色ID
	 */
	private Long roleId;
	
	/**
	 * 系统权限ID
	 */
	private Long permissionId;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id 要设置的  id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId 要设置的  roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return permissionId
	 */
	public Long getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId 要设置的  permissionId
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}