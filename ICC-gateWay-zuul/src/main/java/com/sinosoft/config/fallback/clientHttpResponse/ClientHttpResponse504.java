package com.sinosoft.config.fallback.clientHttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.enums.http.StatusCode;
import com.sinosoft.vo.http.Result;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: zouren
 * @Date: 2019/2/19 09:52
 * @Description:
 */
@Component("clientHttpResponse504")
public class ClientHttpResponse504 extends AbsClientHttpResponse{

    @Override
    public InputStream getBody() throws IOException {
        Result result = Result.failure(StatusCode.SERVICE_TIMEOUT);
        result.setDescription(this.getCause().getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(result);
        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }
}
