package com.example.manticore.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
public class MyBatisConfig {
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:9306/DB/?characterEncoding=utf8&maxAllowedPacket=512000&serverTimezone=Asia/Shanghai";
	public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	public static final String USERNAME = "sa";
	public static final String PASSWORD = "pass123";

	@Bean
	public SqlSessionFactory buildSqlSessionFactory(DataSource dataSource) throws Exception {
		// Register our mappers
		Configuration configuration = new Configuration();
		configuration.addMapper(TestRTMapper.class);

		//DataSource dataSource = new PooledDataSource(DRIVER_CLASS, DB_URL, USERNAME, PASSWORD);

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfiguration(configuration);
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public TestRTMapper testRTMapper(SqlSessionTemplate sqlSessionTemplate) {
		return sqlSessionTemplate.getMapper(TestRTMapper.class);
	}
}
