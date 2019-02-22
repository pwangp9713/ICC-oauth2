package com.sinosoft.oauth2.client.config;

import com.sinosoft.enums.http.StatusCode;
import com.sinosoft.vo.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
* @Description:    异常通用处理
* @Author:         zouren
* @CreateDate:     2019/2/20 17:10
* @Version:        1.0
*/
@RestControllerAdvice
public class ExceptionHandlerAdvice {
	Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * IllegalArgumentException异常处理返回json
	 * 状态码:400
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result badRequestException(IllegalArgumentException exception) {
		logger.info(exception.getMessage());
		Result result = Result.failure(StatusCode.BAD_REQUEST);
		result.setDescription(exception.getMessage());
		return result;
	}
	
	/**
	 * AccessDeniedException异常处理返回json
	 * 状态码:403
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ AccessDeniedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Result badMethodExpressException(AccessDeniedException exception) {
		logger.info(exception.getMessage());

		Result result = Result.failure(StatusCode.FORBIDDEN);
		result.setDescription(exception.getMessage());
		return result;
	}
	
}
