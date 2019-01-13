package com.ysertine.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	@Value("{$mail.fromMail.address}")
	private String form;
	
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
	}

}