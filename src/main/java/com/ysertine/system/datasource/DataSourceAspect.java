package com.ysertine.system.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ysertine.system.annotation.TargetDataSource;
import com.ysertine.system.dynamic.DynamicDataSourceHolder;

/**
 * @Title DataSourceAspect.java
 * @Description 数据源AOP切面定义（4）
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Aspect
@Component
public class DataSourceAspect {
	
	/**
	 * 日志方法
	 */
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
	
	/**
	 * @Title dataSourcePointCut 
	 * @Description 配置AOP切面的切入点，切换放在mapper接口的方法上
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 */
	@Pointcut("execution( * com.ysertine.*.mapper.*.*(..))")
	public void dataSourcePointCut() {
		
	}

	/**
	 * @Title before 
	 * @Description 执行切面方法
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @param joinPoint
	 */
	@Before("dataSourcePointCut()")
	public void before(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		String method = joinPoint.getSignature().getName();
		Class<?>[] clazz = target.getClass().getInterfaces();
		Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = clazz[0].getMethod(method, parameterTypes);
			// 如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
			if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
				TargetDataSource data = m.getAnnotation(TargetDataSource.class);
				String dataSourceName = data.value();
				DynamicDataSourceHolder.putDataSource(dataSourceName);
				logger.debug("current thread " + Thread.currentThread().getName() + " add " + dataSourceName + " to ThreadLocal");
			} else {
				logger.debug("switch datasource fail, use default");
			}
		} catch (Exception e) {
			logger.error("current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", e);
		}
	}

	/**
	 * @Title after 
	 * @Description 执行完切面后，将线程共享中的数据源名称清空
	 * @author DengJinbo
	 * @date 2018年12月25日
	 * @version 1.0
	 * @param joinPoint
	 */
	@After("dataSourcePointCut()")
	public void after(JoinPoint joinPoint) {
		DynamicDataSourceHolder.removeDataSource();
	}
}
