package com.ysertine.system.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ysertine.common.entity.BaseEntity;

/**
 * @Title SysUser.java
 * @Description 系统用户实体类
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Table(name = "sys_user")
@SuppressWarnings("serial")
public class SysUser extends BaseEntity {

	/**
	 * 主键ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 盐
	 */
	private String salt;
	
	/**
	 * 性别：0=保密，1=男性，2=女性
	 */
	private int gender;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 电子邮箱
	 */
	private String email;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 状态：-1=删除，0=禁用，1=正常
	 */
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * @Title SysUser.java
	 * @Description 无参构造函数
	 * @author DengJinbo
	 * @date 2018年12月26日
	 */
	public SysUser() {}

	/**
	 * @Title SysUser.java
	 * @Description 有参构造函数
	 * @author DengJinbo
	 * @date 2018年12月26日
	 * @param username 系统用户名
	 * @param password 密码
	 * @param salt 加密盐值
	 * @param phone 电话号码
	 */
    public SysUser(String username, String password, String salt, String phone) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
    }
}