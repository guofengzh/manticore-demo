package com.example.manticore.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisServiceConfig {
    @Bean
    public TestRTService testRTService(TestRTMapper testRTMapper) {
        return new TestRTService(testRTMapper);
    }
}
