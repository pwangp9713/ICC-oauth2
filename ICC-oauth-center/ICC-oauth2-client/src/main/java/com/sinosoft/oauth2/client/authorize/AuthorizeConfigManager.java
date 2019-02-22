package com.sinosoft.oauth2.client.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;


/**
* @Description:    作用描述
* @Author:         zouren
* @CreateDate:     2019/2/20 17:09
* @Version:        1.0
*/
public interface AuthorizeConfigManager {

	/**
	 * @param config
	 */
	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
