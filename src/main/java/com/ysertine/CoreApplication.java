package com.ysertine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Title CoreApplication.java
 * @Description SpringBoot启动类，主要用于做一些框架配置。 
 *  	SpringBoot会自动Autoconfiguration，由于我们要采用多数据源，这里排除数据库自动配置。
 * @author DengJinbo
 * @date 2018年12月20日
 */
@MapperScan("tk.mybatis.mapper.common.BaseMapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CoreApplication {

	/**
	 * @Title main
	 * @Description SpringBoot项目主函数
	 * @author DengJinbo
	 * @date 2018年12月20日
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}