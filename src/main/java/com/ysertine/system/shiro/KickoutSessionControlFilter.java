package com.ysertine.system.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;

/**
 * @Title KickoutSessionControlFilter.java
 * @Description 
 * @author DengJinbo
 * @date 2019年1月5日
 */
public class KickoutSessionControlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void setCacheManager(RedisCacheManager cacheManager) {
		// TODO Auto-generated method stub
		
	}

	public void setSessionManager(DefaultWebSessionManager sessionManager) {
		// TODO Auto-generated method stub
		
	}

	public void setKickoutAfter(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setMaxSession(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setKickoutUrl(String string) {
		// TODO Auto-generated method stub
		
	}

}
