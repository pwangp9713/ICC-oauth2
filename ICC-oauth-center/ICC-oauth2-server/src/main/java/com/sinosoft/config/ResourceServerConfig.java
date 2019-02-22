package com.sinosoft.config;

import com.sinosoft.erro.security.SecurityAuthenticationEntryPoint;
import com.sinosoft.erro.webResponse.CustomWebResponseExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
        // 定义的不存在access_token时候响应
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/test","/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 定义异常转换类生效
        AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        ((OAuth2AuthenticationEntryPoint) authenticationEntryPoint).setExceptionTranslator(new CustomWebResponseExceptionTranslator());
             resources.authenticationEntryPoint(authenticationEntryPoint);
         }
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/health");
    }
}
