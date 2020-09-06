package com.stcode.bootstrap.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BootstrapDataSource {
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource firstDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

}
