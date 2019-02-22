package com.sinosoft.erro.webResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**验证错误信息入口
 * @Auther: zouren
 * @Date: 2019/2/19 14:52
 * @Description:
 */

public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity responseEntity;
        if (e instanceof InsufficientAuthenticationException){
            AuthenticationException exception = (AuthenticationException) e;
            responseEntity =  ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomOauthException(e.getMessage()));
        } else if (e instanceof OAuth2Exception){
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            responseEntity =  ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        }else {
            responseEntity =  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomOauthException(e.getMessage()));
        }

        return responseEntity;
    }
}
