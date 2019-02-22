package com.sinosoft.tset.service.impl;

import com.sinosoft.tset.service.DB1Service;
import com.sinosoft.tset.service.DB2Service;
import com.sinosoft.tset.service.TestAtomikosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestAtomikosServiceImpl implements TestAtomikosService {
    @Autowired
    private DB1Service db1Service;
    @Autowired
    private DB2Service db2Service;
//    @Transactional
    @Override
    public void savea(String id) {
        db1Service.savedb1(id);
        db2Service.savedb2(id);
//        int i = 1/0;
    }
}
