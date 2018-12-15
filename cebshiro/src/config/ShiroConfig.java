package config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import realm.MyRealm;

@Configuration
public class ShiroConfig {
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		
		Map<String, String> filterChainDefinitionMapping = new HashMap<>();
		filterChainDefinitionMapping.put("/dologin", "anon");
		filterChainDefinitionMapping.put("/logout", "logout");
		filterChainDefinitionMapping.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);

		shiroFilter.setLoginUrl("/login");

		return shiroFilter;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myrealm());
		return securityManager;
	}


	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	public Realm myrealm() {
		MyRealm realm = new MyRealm();
		return realm;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

}
