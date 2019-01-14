package com.ysertine;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title CoreApplicationTest.java
 * @Description SpringBoot项目Junit测试类
 * @author DengJinbo
 * @date 2018年12月20日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoreApplicationTest {

	/**
	 * 端口
	 */
	@LocalServerPort
    private int port;

	/**
	 * 测试链接
	 */
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        // 因为我们修改了 content-path 所以请求后面要带上
        this.base = new URL("http://localhost:" + port + "/hello");
    }

    @Test
    public void hello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertEquals(response.getBody(), "Hello SpringBoot");
    }
}
