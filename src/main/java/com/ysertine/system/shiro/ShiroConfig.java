package com.ysertine.system.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.password}")
	private String password;

	/**
	 * @Title getLifecycleBeanPostProcessor 
	 * @Description Shiro生命周期处理器
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * @Title shiroDialect 
	 * @Description 为了在thymeleaf里使用shiro的标签的bean
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	/*@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}*/
	
	/**
	 * @Title shirFilter 
	 * @Description 拦截器，配置过滤条件和跳转条件，注意：单独一个ShiroFilterFactoryBean配置是会报错的，因为在
	 * 		初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 * 		Filter Chain定义说明 
	 * 		1、一个URL可以配置多个Filter，使用逗号分隔 
	 * 		2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 		3、部分过滤器可指定参数，如perms，roles
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/admin/login");
		shiroFilterFactoryBean.setSuccessUrl("/admin/index");
		
		
		// 拦截器，从上向下顺序执行，一般将 /** 放在最为下边，对所有用户认证
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/common/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/dist/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		// 自定义缓存实现 使用redis
		// securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * @Title myShiroRealm 
	 * @Description 自定义的realm
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * @Title hashedCredentialsMatcher 
	 * @Description 凭证匹配器，密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5"); // 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2); // 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * @Title authorizationAttributeSourceAdvisor 
	 * @Description Shiro AOP注解支持
	 * 		使用代理方式，所以需要开启代码支持
	 * @author DengJinbo
	 * @date 2019年1月14日
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

	/**
	 * @Title redisManager 
	 * @Description 配置shiro redisManager，使用shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800); // 配置缓存过期时间
		redisManager.setTimeout(timeout);
		redisManager.setPassword(password);
		return redisManager;
	}

	/**
	 * @Title cacheManager 
	 * @Description 缓存管理，通过Redis实现，使用shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月14日
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
	 * @Description sessionDao层，通过redis的实现 使用shiro-redis开源插件
	 * @author DengJinbo
	 * @date 2019年1月14日
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
	 * @Description session的管理
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
}