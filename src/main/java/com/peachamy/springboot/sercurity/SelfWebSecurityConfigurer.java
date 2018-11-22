package com.peachamy.springboot.sercurity;

import com.peachamy.springboot.sercurity.jwt.JwtOncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SelfWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Autowired
    MyAuthentication myAuthentication;

    @Autowired
    AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    AjaxAccessDeniedHandler ajaxAccessDeniedHandler;

    @Autowired
    MyUserDtlServence myUserDtlServence;

    @Autowired
    JwtOncePerRequestFilter jwtOncePerRequestFilter;
    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("admin").password("admin").roles("ADMIN")
               .and()
               .withUser("user").password("123").roles("USER");
    }
*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(myAuthentication);

        auth.userDetailsService(myUserDtlServence).passwordEncoder(new BCryptPasswordEncoder());
        /*auth.inMemoryAuthentication()
                .passwordEncoder(ENCODER)
                .withUser("user")
                .password(ENCODER.encode("123456"))
                .roles("USER");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .and()
                .authorizeRequests()//允许基于HttpServletRequest使用限制访问
                //.antMatchers("/user/test").hasRole("ADMIN")
                .anyRequest().access("@authorizeService.hasPerssion(request,authentication)")
                //.authenticated()//其他路径必须验证身份
                .and()
                .formLogin().loginPage("/login")//自定义登录界面
                .successHandler(ajaxAuthenticationSuccessHandler)
                .failureHandler(ajaxAuthenticationFailureHandler)
                .permitAll()
                .and()
                .csrf().disable();


                /*.rememberMe()
                .tokenValiditySeconds(1209600);*/
        http.exceptionHandling().accessDeniedHandler(ajaxAccessDeniedHandler);

        http.addFilterBefore(jwtOncePerRequestFilter,UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }

}
