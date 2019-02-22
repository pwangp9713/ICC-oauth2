package com.sinosoft.controller;

import com.sinosoft.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by zoure on 2018/5/14.
 */
@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserFeignClient userFeignClient;


    @RequestMapping("/getAllOrder")
    public List<String> getAllOrder() {
        logger.info("开始调用");
        return restTemplate.getForObject("http://userSerivce/getAllUser", List.class);
    }

    @RequestMapping("/getAllOrderByFeign")
    public List<String> getAllOrderByFeign() {
        logger.info("order 开始调用 ByFeign");
        return userFeignClient.getAllUser();
    }

    @RequestMapping("/getSerivceName")
    public String getSerivceName() {
        return "this order serivce";
    }

//    public static void main(String[] args) {
//        System.out.printf("wCT+CUlWXJDpKMuSkUFDhg==");
//    }
}

