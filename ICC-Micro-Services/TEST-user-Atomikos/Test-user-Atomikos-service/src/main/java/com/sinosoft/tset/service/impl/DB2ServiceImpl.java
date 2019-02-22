package com.sinosoft.tset.service.impl;

import com.sinosoft.entity.db2.dao.User1Mapper;
import com.sinosoft.entity.db2.model.User1;
import com.sinosoft.tset.service.DB2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DB2ServiceImpl implements DB2Service {
    @Autowired
    private User1Mapper user1Mapper;
    @Override
    public void savedb2(String id) {
        User1 user1 = new User1();
        user1.setId(id);
        user1.setUserName("aa");
        user1Mapper.insertSelective(user1);
    }
}
