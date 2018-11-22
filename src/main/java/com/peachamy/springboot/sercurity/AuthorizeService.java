package com.peachamy.springboot.sercurity;

import org.springframework.http.HttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component("authorizeService")
public class AuthorizeService {
    public boolean hasPerssion(HttpServletRequest request, Authentication authentication){
        String requestUrl = request.getRequestURI();//请求的url

        if (authentication.getPrincipal() instanceof MyUserDtls){//是否认证过
            MyUserDtls myUserDtls = (MyUserDtls) authentication.getPrincipal();//拿到用户信息

            //拿到用户可访问的资源
            Set set = (Set) myUserDtls.getAuthorities();

            //循环匹配
            return true;
        }else{
            return false;
        }

    }
}
