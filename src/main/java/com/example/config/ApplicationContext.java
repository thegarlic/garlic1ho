package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({ SpringMVCContext.class, PersistenceContext.class })
@ComponentScan(basePackages={"com.example.service"})
@PropertySource("classpath:application.properties")
public class ApplicationContext {

	@Autowired DataSource datasource;
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	
	
}
