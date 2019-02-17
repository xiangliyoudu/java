//package com.xlyd.springboot.app_platform.security;
//
//import com.xlyd.springboot.app_platform.service.IDevUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//// register DelegagingFilterProxy to web container
//
//@Configuration
//@EnableWebSecurity
//public class SecurityWebInitializer extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    IDevUserService iDevUserService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//                return iDevUserService.findByName(username);
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .mvcMatchers("/**").permitAll();
//    }
//}
