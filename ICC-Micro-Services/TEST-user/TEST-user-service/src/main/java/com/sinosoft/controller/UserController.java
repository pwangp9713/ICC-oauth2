package com.sinosoft.controller;

import com.sinosoft.entity.dao.Order1Mapper;
import com.sinosoft.entity.model.Order1;
import com.sinosoft.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zoure on 2018/5/14.
 */
@RestController
@RefreshScope
public class UserController {
    private  Logger log = LoggerFactory.getLogger(getClass());
    @Value("${server.port}")
    private String serverPort;
    //@Value("${test.url}")  //通过配置中心得到远程配置
    private String datasourceUrl;
    @Autowired
    private UserService userService;
    @Autowired
    private Order1Mapper order1Mapper;
//    @Autowired
//    private CmsChannelMapper cmsChannelMapper;
    @RequestMapping("/getAllUser")
    public List<String> getAllUser() {
        List<String> list = new ArrayList<String>();
        list.add("aaaaa");
        list.add("bbbb");
        list.add("port:" + serverPort);
        log.info("userService.getAllUser--log");
        return list;
    }
    @RequestMapping("/getSerivceName")
    public String getSerivceName(){
        log.info("getSerivceName--log");
        return "this user serivce,port:" + serverPort+"   ,datasourceUrl:"+datasourceUrl;
    }

    @RequestMapping("/getConfigValue")
    public String getConifgValue(){
        log.info("getConifgValue--log");
        return "datasourceUrl:" + datasourceUrl;
    }
//    @RequestMapping("/testSQL")
//    public CmsChannel testSQL(){
//        log.info("testSQL");
//        CmsChannel cmsChannel = new  CmsChannel ();
////       CmsChannel cmsChannel =  cmsChannelMapper.selectByPrimaryKey("1667f0d1b9b341668fd1744d289b1a6a");
//        cmsChannelMapper.select1();
//        cmsChannelMapper.select2();
//        return cmsChannel ;
//    }
    @RequestMapping("/insert/{id}")
    public String insertMapper(@PathVariable  String id){
        Order1 order = new  Order1();
        order.setId(id);
        order.setOrderName("a");
        order.setCreateDate(new Date());
        order1Mapper.insertSelective(order);
        return "1111";
    }
    @RequestMapping("/update/{id}/{key}")
    @Transactional
    public String inupdateMapper(@PathVariable  String id,@PathVariable  String key){

        Order1 order = new  Order1();
        order.setId(id);
        order.setOrderName(key);
        order1Mapper.updateByPrimaryKeySelective(order);
      //  int a = 1/0;
        return "1111";
    }
    @RequestMapping("/insertS/{id}")
    public String testService(@PathVariable  String id){
        userService.savea(id);

        return "1111";
    }
}

