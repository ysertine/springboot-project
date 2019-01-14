package com.ysertine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ysertine.common.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    /**
     * @Title testSimpleMail 
     * @Description 测试发送简单邮件
     * @author DengJinbo
     * @date 2019年1月14日
     * @version 1.0
     * @throws Exception
     */
    @Test
    public void testSimpleMail() {
    	mailService.sendSimpleMail("335196272@qq.com", "主题：简单邮件", "hello this is simple mail");
    }
    
    /**
     * @Title testHtmlMail 
     * @Description 测试发送HTML邮件
     * @author DengJinbo
     * @date 2019年1月14日
     * @version 1.0
     * @throws Exception
     */
    @Test
    public void testHtmlMail() {
    	String html= "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"   <meta charset=\"UTF-8\">\r\n" + 
				"   <title>网页title</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<font color=\"red\">发送html</font>\r\n" + 
				"</body>\r\n" + 
				"</html>";
    	mailService.sendHtmlMail("335196272@qq.com", "主题：HTML邮件", html);
    } 
    
    /**
     * @Title sendInlineResourceMail 
     * @Description 测试发送带静态资源的邮件
     * @author DengJinbo
     * @date 2019年1月14日
     * @version 1.0
     */
    @Test
    public void sendInlineResourceMail() {
        String resourceId = "fp666";
        String resourcePath = "D:\\Pictures\\photo.jpg";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + resourceId + "\' ></body></html>";

        mailService.sendInlineResourceMail("335196272@qq.com", "主题：这是有图片的邮件", content, resourceId, resourcePath);
    }
    
    /**
     * @Title sendAttachmentsMail 
     * @Description 
     * @author DengJinbo
     * @date 2019年1月14日
     * @version 1.0
     */
    @Test
    public void sendAttachmentsMail() {
        String filePath = "D:\\\\Pictures\\\\Pictures.zip";
        mailService.sendAttachmentsMail("335196272@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }
}