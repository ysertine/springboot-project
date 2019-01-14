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
	 * @param recipient 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendSimpleMail(String recipient, String subject, String content);
	
	/**
	 * @Title sendHtmlMail 
	 * @Description 发送HTML邮件
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param recipient 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendHtmlMail(String recipient, String subject, String content);
	
	/**
	 * @Title sendInlineResourceMail 
	 * @Description 发送带静态资源的邮件
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param recipient 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param resourceId 静态资源ID
	 * @param resourcePath 静态资源路径
	 */
	void sendInlineResourceMail(String recipient, String subject, String content, String resourceId, String resourcePath);
	
	/**
	 * @Title sendAttachmentsMail 
	 * @Description 发送带附件的邮件
	 * @author DengJinbo
	 * @date 2019年1月14日
	 * @version 1.0
	 * @param recipient 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param filePath 附件路径
	 */
	void sendAttachmentsMail(String recipient, String subject, String content, String filePath);
}
