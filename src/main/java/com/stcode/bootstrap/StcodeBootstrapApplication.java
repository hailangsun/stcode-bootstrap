package com.stcode.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stcode.bootstrap.mapper")
public class StcodeBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(StcodeBootstrapApplication.class, args);
    }

}
