package com.sinosoft.config;

import com.sinosoft.erro.security.AuthenticationAccessDeniedHandler;
import com.sinosoft.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /**
    * @Description:    用户认证
    * @Author:         zouren
    * @CreateDate:     2019/1/24 16:06
    * @Version:        1.0
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
    /**
    * @Description: 1:请求授权:
     *                  spring security 使用以下匹配器来匹配请求路劲：
     *                  antMatchers:使用ant风格的路劲匹配
     *                  regexMatchers:使用正则表达式匹配路劲
     *                  anyRequest:匹配所有请求路劲
     *              在匹配了请求路劲后，需要针对当前用户的信息对请求路劲进行安全处理。
     *              2:定制登录行为。
     *                  formLogin()方法定制登录操作
     *                  loginPage()方法定制登录页面访问地址
     *                  defaultSuccessUrl()登录成功后转向的页面
     *              permitAll()
    * @Author:         zouren
    * @CreateDate:     2019/1/24 16:07
    * @Version:        1.0
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/token_key","/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
        .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/plugins/**", "/favicon.ico");
    }

}
