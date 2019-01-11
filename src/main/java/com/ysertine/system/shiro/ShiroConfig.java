package com.ysertine.system.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title ShiroConfig.java
 * @Description Shiro配置类
 * @author DengJinbo
 * @date 2019年1月5日
 */
@Configuration
public class ShiroConfig {

	/**
	 * @Title getLifecycleBeanPostProcessor 
	 * @Description Shiro生命周期处理器
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	/**
	 * @Title getDefaultAdvisorAutoProxyCreator 
	 * @Description 授权所用配置
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
	
	/**
	 * @Title myShiroRealm 
	 * @Description 自定义realm，进行身份认证、账号密码校验、权限控制等
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
	
	/**
	 * @Title redisManager 
	 * @Description 配置Shiro RedisManager，使用的是shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("localhost");
		redisManager.setPort(6379);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(0);
		redisManager.setPassword("123456");
		return redisManager;
	}
	
	/**
	 * @Title cacheManager 
	 * @Description 缓存管理，使用的是shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	
	/**
	 * @Title redisSessionDAO 
	 * @Description 通过redis实现sessionDao层 ，使用的是shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}
	
	/**
	 * @Title sessionManager 
	 * @Description session管理，使用的是shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	/**
	 * @Title securityManager
	 * @Description 权限管理，配置主要是Realm的管理认证
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * @Title shiroFilter 
	 * @Description Filter工厂，设置对应的过滤条件和跳转条件
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/admin/login");
		shiroFilterFactoryBean.setSuccessUrl("/admin/index");
		
		/**
		 *  权限控制map，从上向下顺序执行，一般将/**放在最为下边
		 * authc:所有url都必须认证通过才可以访问；anon:所有url都都可以匿名访问
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/admin/login", "anon");
		filterChainDefinitionMap.put("/admin/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	


	/**
	 * @Title authorizationAttributeSourceAdvisor 
	 * @Description 开启Shiro AOP注解支持。
	 * @author DengJinbo
	 * @date 2019年1月5日
	 * @version 1.0
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}