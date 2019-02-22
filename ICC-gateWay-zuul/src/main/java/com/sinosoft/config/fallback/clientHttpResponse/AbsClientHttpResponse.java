package com.sinosoft.config.fallback.clientHttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.enums.http.StatusCode;
import com.sinosoft.vo.http.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Auther: zouren
 * @Date: 2019/2/19 09:30
 * @Description:
 */
public abstract class   AbsClientHttpResponse implements ClientHttpResponse {
    private HttpStatus status;
    public void setHttpStatus (HttpStatus status){
        this.status = status;
    }
    private Throwable cause;

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return status;
    }
    @Override
    public int getRawStatusCode() throws IOException{
        return status.value();
    }
    @Override
    public String getStatusText() throws IOException{
        return status.getReasonPhrase();
    }
    @Override
    public void close(){
    }
    /**
     * 当 springms-provider-user 微服务出现宕机后，客户端再请求时候就会返回 fallback 等字样的字符串提示；
     * 但对于复杂一点的微服务，我们这里就得好好琢磨该怎么友好提示给用户了；
     * 如果请求用户服务失败，返回什么信息给消费者客户端
     * @return
     * @throws IOException
     */

    @Override
    public InputStream getBody() throws IOException {
        Result result = Result.failure(StatusCode.MICRO_SERVICE_UNAVAILABLE);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(result);
        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }
    @Override
    public HttpHeaders getHeaders(){

        // headers设定
        HttpHeaders headers = new HttpHeaders();

        MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mt);
        return headers;
    }


}
