package com.xlyd.springboot.app_platform;

import com.xlyd.springboot.app_platform.dao._MapperMaker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// scan package, auto implement daoImpl by mapper files
@MapperScan(basePackageClasses = _MapperMaker.class)
public class RootConfig {
}
