package com.sinosoft.tset.service.impl;

import com.sinosoft.entity.db1.dao.Order1Mapper;
import com.sinosoft.entity.db1.model.Order1;
import com.sinosoft.tset.service.DB1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DB1ServiceImpl implements DB1Service {
    @Autowired
    private Order1Mapper order1Mapper;
    @Override
    public void savedb1(String id) {
        Order1 order1 = new Order1();
        order1.setId(id);
        order1.setOrderName("aa");
        order1Mapper.insertSelective(order1);
    }
}
