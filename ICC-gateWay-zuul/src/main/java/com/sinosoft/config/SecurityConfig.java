//package com.sinosoft.config;
//
//import com.sinosoft.config.authentication.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//
//@Configuration
//@EnableWebSecurity
//@Order(3)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    public static String[] permitUrl={"/login", "/uaa/**"};
//    @Autowired
//    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(permitUrl)
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
//    }
//
//
//}
