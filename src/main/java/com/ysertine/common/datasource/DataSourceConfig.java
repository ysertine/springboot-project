package com.ysertine.common.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.ysertine.common.dynamic.DynamicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title DataSourceConfig.java
 * @Description 数据源配置
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Configuration
@EnableScheduling
public class DataSourceConfig {

	/**
	 * 注入实际数据源配置类
	 */
	@Autowired
	private DBProperties properties;

	/**
	 * @Title dataSource 
	 * @Description 获取数据源
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @return
	 */
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		// 按照目标数据源名称和目标数据源对象的映射存放在Map中
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("master", properties.getMaster());
		targetDataSources.put("slave", properties.getSlave());
		// 采用是想AbstractRoutingDataSource的对象包装多数据源
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);
		// 设置默认的数据源，当拿不到数据源时，使用此配置
		dataSource.setDefaultTargetDataSource(properties.getMaster());
		return dataSource;
	}

	/**
	 * @Title txManager 
	 * @Description 事务管理
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}