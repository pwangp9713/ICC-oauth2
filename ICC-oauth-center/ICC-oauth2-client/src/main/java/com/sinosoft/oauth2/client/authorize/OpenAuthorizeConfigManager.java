package com.sinosoft.oauth2.client.authorize;

import com.sinosoft.oauth2.client.service.RbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @Description:    作用描述
* @Author:         zouren
* @CreateDate:     2019/2/20 17:36
* @Version:        1.0
*/
@Component
public class OpenAuthorizeConfigManager implements AuthorizeConfigManager {

	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders;
	
	@Autowired(required=false)
	private RbacService rbacService ;

	/* (non-Javadoc)
	 * @see com.imooc.security.core.authorize.AuthorizeConfigManager#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		 
		//设置访问
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			authorizeConfigProvider.config(config) ;
		}
		
		//token正确登录
		config.anyRequest().authenticated() ;
		
		
		if(rbacService!=null){
// 			放开则全部可以不需要认证访问
 			config
 			.anyRequest()
 				.access("@rbacService.hasPermission(request, authentication)");
		}
		

		
		
	}

}
