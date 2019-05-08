package com.diyinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.diyinsurance.configuration.WebSecurityConfig;

@SpringBootApplication
@Import(value={WebSecurityConfig.class})
/*
 * @SpringBootApplication is a convenience annotation that adds all of the
 * following:
 * 
 * @Configuration tags the class as a source of bean definitions for the
 * application context.
 * 
 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on
 * classpath settings, other beans, and various property settings.
 * 
 * @ComponentScan 
 * tells Spring to look for other components, configurations, and services in com.diyinsurance package
 */ 
public class DiyinsuranceApplication {
//
//	 @Bean
//	    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
//	        return new WebSecurityConfig();
//	    }
	
	
	public static void main(String[] args) {
		//run method return application context level 
		 SpringApplication.run(DiyinsuranceApplication.class, args);
	
	}
}
