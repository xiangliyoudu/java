package config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"dao"})
@EnableTransactionManagement
@PropertySource("classpath:mysqlDB.properties")
public class RootConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public BasicDataSource dataSource(Environment env) {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(env.getProperty("driverClassName"));
		source.setUrl(env.getProperty("url"));
		source.setUsername(env.getProperty("user"));
		source.setPassword(env.getProperty("password"));
		return source;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return template;
	}
	
	@Bean
	public DataSourceTransactionManager txManager(DataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}
	
}












