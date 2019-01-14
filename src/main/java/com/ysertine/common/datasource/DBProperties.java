package com.ysertine.common.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @Title DBProperties.java
 * @Description 实际数据源配置（5）
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Component
@ConfigurationProperties(prefix = "hikari")
public class DBProperties {

	/**
	 * 主数据库
	 */
	private HikariDataSource master;
	
	/**
	 * 从数据库（只读）
	 */
	private HikariDataSource slave;

	public HikariDataSource getMaster() {
		return master;
	}

	public void setMaster(HikariDataSource master) {
		this.master = master;
	}

	public HikariDataSource getSlave() {
		return slave;
	}

	public void setSlave(HikariDataSource slave) {
		this.slave = slave;
	}
}