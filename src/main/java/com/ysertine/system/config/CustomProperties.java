package com.ysertine.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Title CustomProperties.java
 * @Description 映射自定义配置文件实体类
 * @author DengJinbo
 * @date 2018年12月20日
 */
@Component
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "my")
public class CustomProperties {

	/**
	 * 读取配置名为
	 */
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * 重写toString方法
	 */
	@Override
	public String toString() {
		return "{CustomProperties: {" + "age: " + age + ", name: " + name + "}}";
	}
}
