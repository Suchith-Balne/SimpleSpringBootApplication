package com.project.microservices.sampleapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSource dataSource()
    {
        String customUrl = environment.getProperty("spring.datasource.suchith");
         return DataSourceBuilder.create()
                 .driverClassName(environment.getProperty("spring.datasource.driver-class-name"))
                 .url(customUrl)
                 .username(dataSourceProperties.getUsername())
                 .password(dataSourceProperties.getPassword())
                 .build();
    }

}