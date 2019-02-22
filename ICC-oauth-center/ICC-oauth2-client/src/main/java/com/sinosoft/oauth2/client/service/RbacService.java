/**
 * 
 */
package com.sinosoft.oauth2.client.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;


/**
* @Description:    作用描述
* @Author:         zouren
* @CreateDate:     2019/2/20 17:17
* @Version:        1.0
*/
public interface RbacService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
