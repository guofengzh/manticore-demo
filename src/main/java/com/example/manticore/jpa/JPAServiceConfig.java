package com.example.manticore.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAServiceConfig {
    @Bean
    public TestRTJPAService testRTJPAService(TestRTJPARepository testRTJPARepository) {
        return  new TestRTJPAService(testRTJPARepository);
    }
}
