package com.sinosoft.oauth2.client.service.impl;

//import javax.servlet.http.HttpServletRequest;
//
//import com.sinosoft.oauth2.client.service.RbacService;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//import org.springframework.util.AntPathMatcher;
//
//
//
///**
//* @Description:    是否有权限 结合数据库来使用
//* @Author:         zouren
//* @CreateDate:     2019/2/20 17:16
//* @Version:        1.0
//*/
//@Service("rbacService")
//public class RbacServiceImpl implements RbacService {
//
//	private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    /**
//     *
//     * @param request  HttpServletRequest
//     * @param authentication 认证信息
//     * @return 是否有权限
//     */
//	@Override
//	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//		Object principal = authentication.getPrincipal();
//
//		boolean hasPermission = true;
//
//
//
//		return hasPermission;
//	}
//
//}
