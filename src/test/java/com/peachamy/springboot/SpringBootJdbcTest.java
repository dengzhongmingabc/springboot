package com.peachamy.springboot;
  
import java.util.List;

import com.peachamy.springboot.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes=Application.class)// 指定spring-boot的启动类   
//@SpringApplicationConfiguration(classes = Application.class)// 1.4.0 前版本


@AutoConfigureMockMvc
@WebAppConfiguration

public class SpringBootJdbcTest {

    private String dateReg;
    private TestRestTemplate template = new TestRestTemplate();
    @Value("${server.port}")// 注入端口号
    private int port=8888;

    @Test
    public void testHome(){
        String url = "http://localhost:"+8888+"/user/userLogin";
        String result = template.getForObject(url, String.class);
    }

    @Test
    public void testPerson(){
        String url = "http://localhost:"+8888+"/person";
        String result = template.getForObject(url, String.class);
    }
}