package com.ysertine.system.annotation;

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
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TargetDataSource {
	
	/**
	 * 此处接收的是数据源的名称
	 */
	String value();
}