package com.ysertine.system.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ysertine.common.entity.BaseEntity;

/**
 * @Title SysUserRole.java
 * @Description 系统用户角色实体类
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Table(name = "sys_user_role")
@SuppressWarnings("serial")
public class SysUserRole extends BaseEntity {

	/**
	 * 主键ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 系统用户ID
	 */
	private Long userId;
	
	/**
	 * 系统角色ID
	 */
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}