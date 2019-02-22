package com.sinosoft.hystrix;

import com.sinosoft.feign.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignFactory  implements FallbackFactory<UserFeignClient> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private  UserServiceFallBack userServiceFallBack;
    public UserFeignFactory(UserServiceFallBack userServiceFallBack){
        this.userServiceFallBack=userServiceFallBack;
    }
    @Override
    public UserFeignClient create(Throwable throwable) {
        //打印下异常
        logger.error(throwable.getMessage());
//        throwable.printStackTrace();
        return userServiceFallBack;
    }
}
