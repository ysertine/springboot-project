package com.ysertine.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ysertine.common.entity.ThymeleafTest;

/**
 * @Title ThymeleafController.java
 * @Description Thymeleaf模板测试控制类
 * @author DengJinbo
 * @date 2018年12月25日
 */
@Controller
public class ThymeleafTestController {
	
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
	
	@GetMapping("/test")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("thymeleaf");
        // 设置属性
        view.addObject("title", "SpringBoot Project页面");
        view.addObject("desc", "欢迎进入 SpringBoot Project 系统");
        ThymeleafTest author = new ThymeleafTest();
        author.setAge(18);
        author.setEmail("123456@qq.com");
        author.setName("大波波");
        view.addObject("author", author);
        return view;
    }

    @GetMapping("/test1")
    public String index1(HttpServletRequest request) {
        // 与上面的写法不同，但是结果一致。
        // 设置属性
        request.setAttribute("title", "SpringBoot Project页面");
        request.setAttribute("desc", "欢迎进入 SpringBoot Project 系统");
        ThymeleafTest author = new ThymeleafTest();
        author.setAge(18);
        author.setEmail("123456@qq.com");
        author.setName("大波波");
        request.setAttribute("author", author);
        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "thymeleaf";
    }
}