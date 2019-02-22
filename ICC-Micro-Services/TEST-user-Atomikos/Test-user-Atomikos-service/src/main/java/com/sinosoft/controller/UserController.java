package com.sinosoft.controller;

import com.sinosoft.tset.service.TestAtomikosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zoure on 2018/5/14.
 */
@RestController
@RefreshScope
public class UserController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TestAtomikosService testAtomikosService;

    @RequestMapping("/insertS/{id}")
    public String testService(@PathVariable String id) {
        testAtomikosService.savea(id);

        return "1111";
    }
}

