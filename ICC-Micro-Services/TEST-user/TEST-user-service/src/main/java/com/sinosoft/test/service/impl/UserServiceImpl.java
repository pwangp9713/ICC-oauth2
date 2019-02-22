package com.sinosoft.test.service.impl;

import com.sinosoft.entity.dao.Order1Mapper;
import com.sinosoft.entity.dao.User1Mapper;
import com.sinosoft.entity.model.Order1;
import com.sinosoft.entity.model.User1;
import com.sinosoft.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Order1Mapper order1Mapper;
    @Autowired
    private User1Mapper user1Mapper;

    @Override
    public void savea(String id) {
        {
            Order1 order = new  Order1();
            order.setId(id);
            order.setOrderName("a");
            order.setCreateDate(new Date());
            order1Mapper.insertSelective(order);
            User1 u = new User1();
            u.setId(id);
            u.setUserName(id);
            user1Mapper.insertSelective(u);
//            int a = 1/0;
        }
    }


    @Override
    public void adda(String id) {
        Order1 order = new  Order1();
        order.setId(id);
        order.setOrderName("a");
        order.setCreateDate(new Date());
        order1Mapper.insertSelective(order);
        User1 u = new User1();
        u.setId(id);
        u.setUserName(id);
        user1Mapper.insertSelective(u);
        int a = 1/0;
    }
}
