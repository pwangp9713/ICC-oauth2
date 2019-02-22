/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.givemetoken.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 *
 * @author root
 */
@Mapper
public interface MybatisMapper {
    
    @Insert("insert into token (ip, code, accesstoken, refreshtoken, expire, time) values (#{ip}, #{code}, #{accesstoken}, #{refreshtoken}, #{expire}, #{time})")
    public void insertToken(String accesstoken, String refreshtoken, String code, String ip, String expire, String time);
    
}
