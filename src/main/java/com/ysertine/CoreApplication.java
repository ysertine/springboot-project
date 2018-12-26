package com.ysertine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title CoreApplication.java
 * @Description SpringBoot启动类，主要用于做一些框架配置。 
 *  	其中 @RestController 等同于( @Controller 与 @ResponseBody)
 *  	SpringBoot会自动Autoconfiguration，由于我们要采用多数据源，这里排除数据库自动配置。
 * @author DengJinbo
 * @date 2018年12月20日
 */
@RestController
@MapperScan("com.ysertine.*.mapper")
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

	/**
	 * @Title hello
	 * @Description hello world
	 * @author DengJinbo
	 * @date 2018年12月20日
	 * @version 1.0
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		return "Hello SpringBoot";
	}
}
