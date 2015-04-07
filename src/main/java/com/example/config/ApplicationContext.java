package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.example.aop.LucyAspect;

@Configuration
@Import({ SpringMVCContext.class, PersistenceContext.class })
@ComponentScan(basePackages={"com.example.service"})
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class ApplicationContext {

	@Autowired DataSource datasource;
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	//LUCY AOP 
	@Bean LucyAspect lucyAspect(){
		return new LucyAspect();
	}
		
}
