package com.xlyd.springboot.app_platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// scan package, auto implement daoImpl by mapper files
@MapperScan(basePackages = {"com.xlyd.springboot.app_platform.dao"})
public class RootConfig {

}
