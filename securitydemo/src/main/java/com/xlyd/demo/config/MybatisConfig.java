package com.xlyd.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.xlyd.demo.dao._MapperMarker;

@Configuration
@MapperScan(basePackageClasses = {_MapperMarker.class})
public class MybatisConfig {

}
