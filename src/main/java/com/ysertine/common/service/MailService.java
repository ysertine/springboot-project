package com.ysertine.common.service;

/**
 * @Title MailService.java
 * @Description 邮件服务Service接口
 * @author DengJinbo
 * @date 2019年1月13日
 */
public interface MailService {

	/**
	 * @Title sendSimpleMail 
	 * @Description 发送简单的邮件
	 * @author DengJinbo
	 * @date 2019年1月13日
	 * @version 1.0
	 * @param to 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendSimpleMail(String to, String subject, String content);
}
