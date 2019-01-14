package com.ysertine.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title TargetDataSource.java
 * @Description 自定义数据源切换方法注解（3），注解在方法上指定数据源的名称
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
	
	/**
	 * @Title value 
	 * @Description 数据源的名称，需要跟DBProperties中配置的数据源名称对应
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @return
	 */
	String value();
}