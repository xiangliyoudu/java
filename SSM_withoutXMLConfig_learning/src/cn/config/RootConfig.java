package cn.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan(basePackages = { "cn.service" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
// 启用事物管理器
//@EnableTransactionManagement
@PropertySource("classpath:mysqlDB.properties")
//@ImportResource("classpath:spring-mybatis.xml")
public class RootConfig {

	@Autowired
	Environment env;
	
	/**
	 * 数据源
	 * 
	 * @return
	 */
	@Bean
	public BasicDataSource dataSource(Environment env) {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(env.getProperty("driverClassName"));
		source.setUrl(env.getProperty("url"));
		source.setUsername(env.getProperty("user"));
		source.setPassword(env.getProperty("password"));
		return source;
	}

	/**
	 * Mybatis sqlSessionFactory bean
	 * 
	 * @param dataSource
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(BasicDataSource dataSource,
			org.apache.ibatis.session.Configuration mybatisConfig)
			throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		// datasource
		factory.setDataSource(dataSource);
		// typeAliases
		factory.setTypeAliasesPackage("cn.pojo");
		// pattern resolver
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// mapper files
		factory.setMapperLocations(resolver
				.getResources("classpath:cn/dao/*Mapper.xml"));
		// config xml
		// factory.setConfigLocation(resolver.getResource("class:mybatis-config.xml"));
		// config bean
		factory.setConfiguration(mybatisConfig);
		return factory;
	}
	
	
	/** mybatis分页插件pagehelper
	 * @return
	 */
	@Bean
	public Interceptor interceptor() {
		PageInterceptor plug = new PageInterceptor();
		// pageInterceptor properties
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		plug.setProperties(properties );
		return plug;
	}

	/**
	 * mybatis configuration class (eq mybatis-config.xml)
	 * 
	 * @return
	 */
	@Bean
	public org.apache.ibatis.session.Configuration mybatisConfig(Interceptor interceptor) {
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		// 该配置影响的所有映射器中配置的缓存的全局开关
		config.setCacheEnabled(true);
		// 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态
		config.setLazyLoadingEnabled(true);
		// 是否允许单一语句返回多结果集（需要兼容驱动）
		config.setMultipleResultSetsEnabled(true);
		// 使用列标签代替列名。不同的驱动在这方面会有不同的表现，具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果
		config.setUseColumnLabel(true);
		// 允许 JDBC 支持自动生成主键，需要驱动兼容
		config.setUseGeneratedKeys(true);
		// 指定 MyBatis 是否以及如何自动映射指定的列到字段或属性
		config.setAutoMappingBehavior(AutoMappingBehavior.FULL);
		// 配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）；
		// BATCH 执行器将重用语句并执行批量更新
		config.setDefaultExecutorType(ExecutorType.SIMPLE);
		// 设置超时时间，它决定驱动等待数据库响应的秒数
		config.setDefaultStatementTimeout(25);
		// 允许在嵌套语句中使用行分界（RowBounds）
		config.setSafeRowBoundsEnabled(false);
		// 是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn
		// 的类似映射
		config.setMapUnderscoreToCamelCase(false);
		// MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。
		// 默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。若设置值为 STATEMENT，本地会话仅用在语句执行上，
		// 对相同 SqlSession 的不同调用将不会共享数据
		config.setLocalCacheScope(LocalCacheScope.SESSION);
		// 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。某些驱动需要指定列的 JDBC 类型，
		// 多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER
		config.setJdbcTypeForNull(JdbcType.NULL);
		// 指定哪个对象的方法触发一次延迟加载
		Set<String> methods = new HashSet<String>();
		methods.add("equal");
		methods.add("hashCode");
		methods.add("toString");
		config.setLazyLoadTriggerMethods(methods);
		// 添加分页插件拦截器 PageInterceptor
		config.addInterceptor(interceptor);
		return config;
	}

	/**
	 * mybatis 扫描dao包，自动实现接口
	 * 
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer msConfigurer() {
		MapperScannerConfigurer config = new MapperScannerConfigurer();
		config.setBasePackage("cn.dao");
		return config;
	}

	/**
	 * 事务管理器
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager txManager(DataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}
	

	
}









