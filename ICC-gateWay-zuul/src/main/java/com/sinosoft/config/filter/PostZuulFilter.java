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

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**在这个可以做到对所有进入的连接进度过滤
 * 暂时没有加入spring 不起作用，想要使追加代码 并打开component 注释
 * Created by zoure on 2018/5/15.
 */
@Component
public class PostZuulFilter extends ZuulFilter {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientHttpResponseFactory clientHttpResponseFactory;
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.info("==========post====================");

            HttpServletRequest request = ctx.getRequest();
        RequestContext context = RequestContext.getCurrentContext();

//        try {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(404);
//            ctx.getResponse().getWriter().write("toke is empty");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
