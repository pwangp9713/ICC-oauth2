package com.sinosoft.oauth2.client.authorize.provider;

import com.sinosoft.oauth2.client.authorize.AuthorizeConfigProvider;
import com.sinosoft.oauth2.client.config.PermitUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


/**
* @Description:    白名单
* @Author:         zouren
* @CreateDate:     2019/2/20 17:09
* @Version:        1.0
*/
@Component
@Order(Integer.MAX_VALUE - 1)
@EnableConfigurationProperties(PermitUrlProperties.class)
public class AuthAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired(required = false)
	private PermitUrlProperties permitUrlProperties;

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

		// 免token登录设置
		config.antMatchers(permitUrlProperties.getIgnored()).permitAll();
		//前后分离时需要带上
		config.antMatchers(HttpMethod.OPTIONS).permitAll();
		//TODO 为了测试加上post与get
//		config.antMatchers(HttpMethod.POST).permitAll();
//		config.antMatchers(HttpMethod.GET).permitAll();
		return true;
	}

}
