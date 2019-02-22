package com.sinosoft.config.fallback;

import com.sinosoft.config.fallback.clientHttpResponse.AbsClientHttpResponse;
import com.sinosoft.config.fallback.clientHttpResponse.ClientHttpResponse40001;
import com.sinosoft.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @Auther: zouren
 * @Date: 2019/2/19 09:28
 * @Description:
 */
@Component
public class ClientHttpResponseFactory {
    @Autowired
    private SpringUtil springUtil;
    public ClientHttpResponse getClientHttpResponse(HttpStatus httpStatus,Throwable cause){
        AbsClientHttpResponse clientHttpResponse= null;
        try {
            clientHttpResponse = (AbsClientHttpResponse)springUtil.getBean("clientHttpResponse"+httpStatus.value());
        } catch (Exception e) {
            clientHttpResponse = (AbsClientHttpResponse)springUtil.getBean("clientHttpResponse500");
        }
        clientHttpResponse.setHttpStatus(httpStatus);
        clientHttpResponse.setCause(cause);
        return clientHttpResponse;
    }
}
