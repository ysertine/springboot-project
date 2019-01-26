package com.ysertine.system.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ysertine.common.entity.BaseEntity;

/**
 * @Title SysPermission.java
 * @Description 系统权限实体类
 * @author DengJinbo
 * @date 2019年1月7日
 */
@Table(name = "sys_permission")
@SuppressWarnings("serial")
public class SysPermission extends BaseEntity {

	/**
	 * 主键ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 权限名称
	 */
	private String name;
	
	/**
	 * 权限字符串
	 */
	private String permission;
	
	/**
	 * 资源路径
	 */
	private String resourceUrl;
	
	/**
	 * 资源类型：1=菜单，2=按钮
	 */
	private Integer resourceType;
	
	/**
	 * 父类资源ID
	 */
	private Long parentId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}