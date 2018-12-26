package com.ysertine.system.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Title DynamicDataSource.java
 * @Description 动态数据源实现类（2），实现动态数据源AbstractRoutingDataSource
 * @author DengJinbo
 * @date 2018年12月25日
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	/**
	 * 数据源路由，此方用于产生要选取的数据源逻辑名称
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// 从共享线程中获取数据源名称
		return DynamicDataSourceHolder.getDataSource();
	}
}
