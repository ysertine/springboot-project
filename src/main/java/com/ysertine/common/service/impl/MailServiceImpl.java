package com.ysertine.common.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ysertine.common.service.MailService;

/**
 * @Title MailServiceImpl.java
 * @Description MailService实现类
 * @author DengJinbo
 * @date 2019年1月13日
 */
@Service
public class MailServiceImpl implements MailService {

	/**
	 * 日志类
	 */
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 发件人
	 */
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public void sendSimpleMail(String recipient, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(recipient);
		message.setSubject(subject);
		message.setText(content);

		try {
			mailSender.send(message);
			logger.info("发送给 " + recipient + " 的简单邮件已经发送。 主题：" + subject);
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
		}
	}

	@Override
	public void sendHtmlMail(String recipient, String subject, String content) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(recipient);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(content, true);
			mailSender.send(mimeMessage);
			logger.info("发送给：" + recipient + " 的HTML邮件已经发送。 主题：" + subject);
		} catch (MessagingException e) {
			logger.info("发送给：" + recipient + " 的HTML邮件发生异常。主题：" + subject);
			e.printStackTrace();
		}

	}

	@Override
	public void sendInlineResourceMail(String recipient, String subject, String content, String resourceId,
			String resourcePath) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

	    try {
	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	        mimeMessageHelper.setFrom(sender);
	        mimeMessageHelper.setTo(recipient);
	        mimeMessageHelper.setSubject(subject);
	        mimeMessageHelper.setText(content, true);

	        FileSystemResource resource = new FileSystemResource(new File(resourcePath));
	        mimeMessageHelper.addInline(resourceId, resource);

	        mailSender.send(mimeMessage);
	        logger.info("发送给：" + recipient + " 的嵌入静态资源的邮件已经发送。 主题：" + subject);
		} catch (MessagingException e) {
			logger.info("发送给：" + recipient + " 的嵌入静态资源的邮件发生异常。主题：" + subject);
			e.printStackTrace();
		}
	}

	@Override
	public void sendAttachmentsMail(String recipient, String subject, String content, String filePath) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

	    try {
	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	        mimeMessageHelper.setFrom(sender);
	        mimeMessageHelper.setTo(recipient);
	        mimeMessageHelper.setSubject(subject);
	        mimeMessageHelper.setText(content, true);

	        FileSystemResource file = new FileSystemResource(new File(filePath));
	        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
	        mimeMessageHelper.addAttachment(fileName, file);
	        
	        mailSender.send(mimeMessage);
	        logger.info("发送给：" + recipient + " 的带附件的邮件已经发送。 主题：" + subject);
		} catch (MessagingException e) {
			logger.info("发送给：" + recipient + " 的带附件的邮件发生异常。主题：" + subject);
			e.printStackTrace();
		}
	}
}