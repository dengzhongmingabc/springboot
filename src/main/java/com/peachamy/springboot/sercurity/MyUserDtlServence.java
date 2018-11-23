package com.peachamy.springboot.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyUserDtlServence implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户信息
        MyUserDtls user = new MyUserDtls();
        user.setUsername("admim");
        user.setPassword(new BCryptPasswordEncoder().encode("123"));

        Set roles = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN123");
        roles.add(grantedAuthority);

        user.setAuthorities(roles);
        return user;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
