package com.sinosoft.hystrix;

import com.sinosoft.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoure on 2018/5/16.
 */
@Component
public class UserServiceFallBack implements UserFeignClient {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<String> getAllUser() {
        //错误处理方法
        List<String> re = new ArrayList<String>(2);
        re.add("getAllUser is fallback ");
        return re;
    }
}
