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
	private Long role_id;
	
	/**
	 * 系统权限ID
	 */
	private Long permission_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public Long getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Long permission_id) {
		this.permission_id = permission_id;
	}
}