package com.sinosoft.feign;

import com.sinosoft.hystrix.UserFeignFactory;
import com.sinosoft.hystrix.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/** 使用feign 调用注册中心的服务
 * FeignClient中的value对应服务名,fallbac降级处理
 *
 * Created by zoure on 2018/5/16.
 */
@FeignClient(value = "userSerivce"
//        ,fallback = UserServiceFallBack.class
        , fallbackFactory = UserFeignFactory.class)
public interface UserFeignClient {
    //对应服务下的请求
    @RequestMapping("/getAllUser")
    public List<String> getAllUser();
}
