package com.xlyd.springboot.app_platform;

import com.xlyd.springboot.app_platform.dao._MapperMaker;
import com.xlyd.springboot.app_platform.interceptor.CustomerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.ArrayList;
import java.util.List;

@Configuration
// scan package, auto implement daoImpl by mapper files
@MapperScan(basePackageClasses = _MapperMaker.class)
public class RootConfig {

    @Bean
    public InterceptorRegistry interceptorRegistry() {
        List<String> patterns = new ArrayList<>();
        patterns.add("/manager/backend/**");
        patterns.add("/dev/flatform/**");
        InterceptorRegistry registry = new InterceptorRegistry();
        registry.addInterceptor(new CustomerInterceptor())
                .addPathPatterns(patterns);
        return registry;
    }
}
