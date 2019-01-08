package com.ysertine.system.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Title ExceptionHandler.java
 * @Description 全局异常处理类
 * @author DengJinbo
 * @date 2019年1月8日
 */
@ControllerAdvice
public class CtrlExceptionHandler {

	/**
	 * 日志方法
	 */
	private static Logger logger = LoggerFactory.getLogger(CtrlExceptionHandler.class);
	
	/**
	 * @Title handleUnauthorizedException 
	 * @Description 拦截未授权页面
	 * @author DengJinbo
	 * @date 2019年1月8日
	 * @version 1.0
	 * @param e
	 * @return
	 */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        logger.debug(e.getMessage());
        return "403";
    }

    /**
     * @Title handleAuthorizationException 
     * @Description 拦截授权异常
     * @author DengJinbo
     * @date 2019年1月8日
     * @version 1.0
     * @param e
     * @return
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException(AuthorizationException e) {
        logger.debug(e.getMessage());
        return "403";
    }
}
