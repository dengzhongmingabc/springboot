package com.peachamy.springboot.sercurity;

import com.alibaba.fastjson.JSON;
import com.peachamy.springboot.sercurity.jwt.JwtTokenUtil;
import com.peachamy.springboot.sercurity.jwt.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setCode("00");
        responseBody.setMsg("login success");

        MyUserDtls userDtls = (MyUserDtls) authentication.getPrincipal();

        String token = JwtTokenUtil.generateToken(userDtls);

        responseBody.setJwtToken(token);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
