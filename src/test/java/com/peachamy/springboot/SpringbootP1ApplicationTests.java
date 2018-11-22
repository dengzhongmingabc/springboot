package com.peachamy.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peachamy.springboot.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration


@SpringBootTest(classes = Application.class)// 指定spring-boot的启动类


public class SpringbootP1ApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(SpringbootP1ApplicationTests.class);

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void test1() throws Exception {
        log.debug("测试get请求无参数......");

        //mock进行模拟
        mvc.perform(MockMvcRequestBuilders.get("/user/userLogin").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void test2() throws Exception {
        log.debug("测试get请求带参数......");

        String myName = "debug-steadyjack-大圣";
        //mock进行模拟
        mvc.perform(MockMvcRequestBuilders.get("/user/getUserDtoByName").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("name", myName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }


	/*@Test
	public void test3() throws Exception{
		log.debug("测试post请求带参数......");

			UserDto userDto=new UserDto();
			userDto.setUserName("debug--大圣");
			userDto.setAddress("北京大同");
			userDto.setSex("男的");
			log.debug("post 参数： {}",userDto);

		ObjectMapper objectMapper=new ObjectMapper();
		String requestJson=objectMapper.writeValueAsString(userDto);

		//mock进行模拟
		mvc.perform(MockMvcRequestBuilders.post("/user/saveUserDto").accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}*/
}