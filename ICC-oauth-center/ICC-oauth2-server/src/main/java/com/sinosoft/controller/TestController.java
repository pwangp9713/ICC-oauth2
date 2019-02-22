package com.sinosoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public @ResponseBody
    String revokeToken( ){

        return "test";
    }
    @RequestMapping(value = "/bb")
    public @ResponseBody
    String bb( ){

        return "bb";
    }
}
