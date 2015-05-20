package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.example.aop.LucyAspect4BoardArticle;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewInterceptor;

@Configuration
@Import({ SpringMVCContext.class, PersistenceContext.class, SecurityContext.class, SocialContext.class })
@ComponentScan(basePackages = { "com.example.service" })
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class ApplicationContext {

	@Autowired
	DataSource datasource;

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}



	// LUCY AOP
	@Bean
	LucyAspect4BoardArticle lucyAspect() {
		return new LucyAspect4BoardArticle();
	}

}
