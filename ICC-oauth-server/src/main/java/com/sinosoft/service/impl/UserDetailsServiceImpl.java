package com.sinosoft.service.impl;

import com.sinosoft.entity.dao.BaseUserMapper;
import com.sinosoft.entity.model.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * @Auther: zouren
 * @Date: 2019/1/24 16:14
 * @Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //@Autowired
    //private BaseUserMapper baseUserMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        logger.info("LoginID : {}",loginId);
        //TODO 需要修改查数据得到帐号密码返回，之后框架会自动与传入的比较
//        BaseUser baseUser = new BaseUser();
//        baseUser.setUserName(loginId);
//        baseUser = baseUserMapper.selectOne(baseUser);
//
//        if(Objects.isNull(baseUser)){
//            throw new UsernameNotFoundException("User " + loginId + " was not found in the database");
//        }
//
//        Collection<GrantedAuthority> grantedAuthorities =  AuthorityUtils.createAuthorityList("ROLE_USER");
//
//
//        //返回一个SpringSecurity需要的用户对象
//        return new org.springframework.security.core.userdetails.User(
//                baseUser.getUserName(),
//                baseUser.getPassword(),
//                grantedAuthorities);
        if (true||"admin".equalsIgnoreCase(loginId)) {
            User user = mockUser();
            return user;
        }
        return null;

    }
    private User mockUser() {

        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        User user = new User("admin","{bcrypt}123456",authorities);
        User user = new User("admin","$2a$10$6lIOO2/uSAjzEP37.X1qD.orufLsKXQQ4JIsimxPrNpvmpCPuRnCS",authorities);
        return user;
    }
}
