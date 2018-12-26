package com.ysertine.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title SysUser.java
 * @Description 系统用户实体类
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Table(name = "sys_user")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 8655851615465363473L;
	
	/**
	 * 主键ID
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 盐
	 */
	private String salt;
	
	/**
	 * 性别：1=男性，2=女性
	 */
	private Integer sex;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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
	 * @param userName 系统用户名
	 * @param password 密码
	 * @param salt 加密盐值
	 * @param phone 电话号码
	 */
    public SysUser(String userName, String password, String salt, String phone) {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
    }

	// 密码加盐，盐值由数据库中的salt和账号组合而成
    public String getCredentialsSalt() {
        return this.userName + this.salt;
    }	
}