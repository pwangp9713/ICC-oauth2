package com.sinosoft.erro.security;

import com.sinosoft.enums.http.StatusCode;
import com.sinosoft.vo.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: zouren
 * @Date: 2019/2/19 15:20
 * @Description:
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Spring Securtiy异常", authException);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Result result = Result.failure(StatusCode.FORBIDDEN);
        result.setDescription(authException.getMessage());
        result.setPath(request.getServletPath());
        out.write( result.toJSONString());
    }
}
