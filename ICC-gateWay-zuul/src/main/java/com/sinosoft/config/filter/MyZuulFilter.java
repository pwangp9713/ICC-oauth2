package com.sinosoft.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sinosoft.config.fallback.ClientHttpResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**在这个可以做到对所有进入的连接进度过滤
 * 暂时没有加入spring 不起作用，想要使追加代码 并打开component 注释
 * Created by zoure on 2018/5/15.
 */
@Component
public class MyZuulFilter extends ZuulFilter {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private ClientHttpResponseFactory clientHttpResponseFactory;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.info("==========pre====================");
            HttpServletRequest request = ctx.getRequest();
            Object accessToken = request.getParameter("toke");
            if (accessToken!=null){
                return null;
            }
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.getResponse().getWriter().write("toke is empty");

        return null;
    }
}
