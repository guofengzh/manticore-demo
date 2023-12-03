package com.example.manticore;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:9306/DB/?characterEncoding=utf8&maxAllowedPacket=512000&serverTimezone=Asia/Shanghai";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    @Bean
    public DataSource dataSource() {
        return createHikariDataSource();
    }

    private HikariDataSource createHikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setDriverClassName(DRIVER_CLASS);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.setMaximumPoolSize(10);
        return new HikariDataSource(config);
    }
}
