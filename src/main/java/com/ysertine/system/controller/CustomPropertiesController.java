package com.ysertine.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysertine.system.config.CustomProperties;

/**
 * @Title CustomPropertiesController.java
 * @Description 自定义配置文件控制器
 * @author DengJinbo
 * @date 2018年12月20日
 */
@RestController
@RequestMapping("/customProperties")
public class CustomPropertiesController {

	/**
	 * 日志方法
	 */
	private static final Logger  logger = LoggerFactory.getLogger(CustomPropertiesController.class);
	
	/**
	 * 注入CustomProperties
	 */
	private CustomProperties customProperties;
	
	/**
	 * @Title CustomPropertiesController.java
	 * @Description Spring4.x 以后，推荐使用构造函数的形式注入属性
	 * @author DengJinbo
	 * @date 2018年12月20日
	 * @param customProperties
	 */
	@Autowired
    public CustomPropertiesController(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }
	
	/**
	 * @Title customProperties 
	 * @Description 测试读取自定义配置文件
	 * @author DengJinbo
	 * @date 2018年12月20日
	 * @version 1.0
	 * @return
	 */
	@GetMapping("/print")
    public CustomProperties customProperties() {
        logger.info("===================================");
        logger.info(customProperties.toString());
        logger.info("===================================");
        return customProperties;
    }
}