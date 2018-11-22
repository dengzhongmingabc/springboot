package com.peachamy.springboot.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthentication implements AuthenticationProvider {

    @Autowired
    MyUserDtlServence myUserDtlServence;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String)authentication.getPrincipal();
        String password =  (String)authentication.getCredentials();


        MyUserDtls myUserDtls = (MyUserDtls) myUserDtlServence.loadUserByUsername(username);

        if (!myUserDtls.getPassword().equals(password)){
            throw new BadCredentialsException("账号密码不正确！");
        }
        return new UsernamePasswordAuthenticationToken(myUserDtls,password,myUserDtls.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
