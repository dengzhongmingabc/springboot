package com.peachamy.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexTest {
    private String dateReg;
    private TestRestTemplate template = new TestRestTemplate();
    @Value("${server.port}")// 注入端口号
    private int port;

    @Test
    public void testHome(){
        String url = "http://localhost:"+8888+"/user/userLogin";
        String result = template.getForObject(url, String.class);
    }

    @Test
    public void testPerson(){
        String url = "http://localhost:"+port+"/person";
        String result = template.getForObject(url, String.class);
    }
}