package com.xlyd.demo.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xlyd.demo.filter$listener._FilterListenerMarker;

@Configuration
@ServletComponentScan(basePackageClasses = { _FilterListenerMarker.class })
public class FilterListenerConfig {

}
