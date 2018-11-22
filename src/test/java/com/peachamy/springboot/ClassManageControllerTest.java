package com.peachamy.springboot;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
/**此处可以是静态引用，也可以不用静态引用，这样就需要在使用方法的时候加上对应的类*/
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ClassManageControllerTest {

   @Autowired
   private WebApplicationContext context;
   private MockMvc mvc;

   @Before
   public void setUp() throws Exception {
      mvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc

   }

   @Test
   public void signOutCurrentClass() throws Exception {
      mvc.perform(post("/user/userLogin") // //调用接口
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .param("userId", "11").param("userName", "henry")//传入添加的用户参数 

            .accept(MediaType.APPLICATION_JSON))  //接收的类型
            .andExpect(status().isOk())   //判断接收到的状态是否是200
            .andDo(print())  //打印内容
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(content().string(Matchers.containsString("OK"))) //匹配返回值中的内容
            .andExpect(jsonPath("$.errcode", is(0)));////使用jsonPath解析返回值，判断具体的内容 

   }

   @Test
   public void testInfo() throws Exception {
//    JSONObject param = new JSONObject() ;
//    param.put("userId", "11");
//    param.put("userName", "henry");
//    String jsonstr = param.toString() ;
//    System.out.println("================================请求入参："+jsonstr);
      RequestBuilder request = post("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .param("userId", "11").param("userName", "henry");
//    RequestBuilder request = MockMvcRequestBuilders.post("/classmanage/signOutCurrentClass")
//          .contentType(MediaType.APPLICATION_JSON)
//          .content(jsonstr).accept(MediaType.APPLICATION_JSON);

      MvcResult mvcResult = mvc.perform(request).andReturn();
      int status = mvcResult.getResponse().getStatus();
      String content = mvcResult.getResponse().getContentAsString();
      System.out.println("返回结果：" + status);
      System.out.println(content);

   }

}