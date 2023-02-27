package com.reggie_takeout;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;


@SpringBootApplication
@ServletComponentScan
public class ReggieTakeoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeoutApplication.class, args);
    }

}
