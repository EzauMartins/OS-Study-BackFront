package com.ezau.os.osAppFullStack.config;

import com.ezau.os.osAppFullStack.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBservice dBservice;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Bean
    public Boolean instaciaDB(){

        if(ddl.equals("create")){
            this.dBservice.instaciaDB();
        }

        return false;

    }

}
