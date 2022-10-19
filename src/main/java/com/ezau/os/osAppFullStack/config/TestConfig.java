package com.ezau.os.osAppFullStack.config;

import com.ezau.os.osAppFullStack.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dBservice;

    @Bean
    public void instaciaDB(){
        this.dBservice.instaciaDB();
    }

}
