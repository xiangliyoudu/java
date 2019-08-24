package com.xlyd.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(new UserDetailsService() {
			
				@Override
				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
					GrantedAuthority role = new SimpleGrantedAuthority("dev");
					List<GrantedAuthority> roles = new ArrayList<>();
					roles.add(role);
					String pwd = encoder.encode("123");
					return new User("tom", pwd, true, true,
							true, true, roles);
				}
			})
			.passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/login")
			.and()
			.rememberMe()
				.key("test-key")
			.and()
			.authorizeRequests()
				.antMatchers("/product/**").authenticated();
	}

}
