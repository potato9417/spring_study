package com.javateam.myBatisTransactionSample.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.ognl.ParseException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
// import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author javateam
 *
 */
@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages="com.javateam.myBatisTransactionSample.domain")//, 
			//sqlSessionFactoryRef="dataSourceDBCP", 
			//sqlSessionTemplateRef="sqlSessionFactoryBean")
@EnableTransactionManagement
// public class DbConfig extends WebMvcConfigurationSupport {
public class DbConfig implements WebMvcConfigurer {
	
	@Bean // jdbc connection
	public DataSource getDataSourceJDBC() {
		
		
		SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		sdds.setDriverClass(oracle.jdbc.OracleDriver.class);
		sdds.setUrl("jdbc:oracle:thin:@localhost:1521:xe"); sdds.setUsername("hr");
		sdds.setPassword("hr");
		
		return sdds;
		
		
		/* 
		 * DriverManagerDataSource dmDataSrc = new DriverManagerDataSource();
		 * 
		 * dmDataSrc.setDriverClassName("oracle.jdbc.OracleDriver"); //
		 * dmDataSrc.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		 * dmDataSrc.setUrl("jdbc:oracle:thin:@localhost:1521:xe"); //
		 * dmDataSrc.setUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		 * 
		 * dmDataSrc.setUsername("hr"); dmDataSrc.setPassword("hr");
		 * 
		 * return dmDataSrc;
		 */
		
		
	} //
	
//	@Bean // (name="dataSourceDBCP") // DBCP connection
//	public DataSource getDataSourceDBCP() {
//		
//		BasicDataSource bd = new BasicDataSource();
//		bd.setDriverClassName("oracle.jdbc.OracleDriver");
//		bd.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		bd.setUsername("hr");
//		bd.setPassword("hr");
//		bd.setMaxTotal(10);
//		bd.setMaxIdle(5);
//		bd.setMaxWaitMillis(-1);
//		
//		return bd;		
//	} //
	
	// for log4jdbc
	@Bean // (name="dataSourceDBCP") // DBCP connection
	public DataSource getDataSourceDBCP() {
		
		BasicDataSource bd = new BasicDataSource();
		bd.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		bd.setUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		bd.setUsername("hr");
		bd.setPassword("hr");
		bd.setMaxTotal(10);
		bd.setMaxIdle(5);
		bd.setMaxWaitMillis(-1);
		
		return bd;		
	} //
	
	@Bean
	// public SqlSessionFactoryBean getSqlSessionFactoryBean() throws Exception {
	public SqlSessionFactory getSqlSessionFactoryBean() throws Exception {
		
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		
		Properties props = new Properties();
		props.setProperty("mapUnderscoreToCamelCase", "true");
		ssfb.setConfigurationProperties(props);		
				
		ssfb.setTypeAliasesPackage("com.javateam.myBatisTransactionSample.domain");
		ssfb.setDataSource(this.getDataSourceJDBC());
		
		ssfb.setMapperLocations(new PathMatchingResourcePatternResolver()
			.getResources("classpath:/mapper/*.xml"));

		/*PathMatchingResourcePatternResolver resolver 
			= new PathMatchingResourcePatternResolver();
	    Resource[] resources 
	    	= resolver.getResources("classpath:/mapper/*.xml");

	    ssfb.setMapperLocations(resources);*/
		
		// return (SqlSessionFactoryBean) ssfb.getObject();
		return ssfb.getObject();
	} //
	
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
		
		SqlSessionTemplate sst 
			= new SqlSessionTemplate((SqlSessionFactory) this.getSqlSessionFactoryBean());
			// = new SqlSessionTemplate(this.getSqlSessionFactoryBean());

		return sst;
	} //
	
	////////////////////////////////////////////////////////////////////////////////////////
	// 주의 사항) @Transactional 사용시 PlatformTransactionManager 혹은 
	// DataSourceTransactionManager 둘 중 하나의 트랜잭션 관리자만 사용하도록 할 것 : 에러 유발 !
	@Bean
    public PlatformTransactionManager getTransactionManager() 
    			throws URISyntaxException, GeneralSecurityException, ParseException, IOException {
        
		return new DataSourceTransactionManager(this.getDataSourceJDBC());
    } //
	
	/*@Bean
	public DataSourceTransactionManager getTransactionManager2() {
		
		DataSourceTransactionManager dstm 
			= new DataSourceTransactionManager(this.getDataSourceJDBC());
		
		return dstm;
	} //
	 */	 	
} //