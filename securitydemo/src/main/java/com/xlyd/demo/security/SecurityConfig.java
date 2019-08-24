package com.xlyd.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.xlyd.demo.dao.UserDao;
import com.xlyd.demo.util.CryptUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDao userDao;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(new SysUserDetailsService(userDao))
		.passwordEncoder(CryptUtils.getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()
		.and()
		.authorizeRequests()
			.antMatchers("/sectest/test/**").authenticated()
			.antMatchers("/sectest/do/**").hasAuthority("SMBMS_ADMIN")
			.anyRequest().permitAll()
			;
	}
	
	

}
