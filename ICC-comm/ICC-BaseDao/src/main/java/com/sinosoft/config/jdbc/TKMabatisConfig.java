package com.sinosoft.config.jdbc;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages="com.sinosoft.entity.dao")
public  class TKMabatisConfig {
// 
}
