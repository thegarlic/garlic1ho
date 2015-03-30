package com.example.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = { "com.example.controller" })
@EnableWebMvc
public class SpringMVCContext extends WebMvcConfigurerAdapter {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}
	/*@Override
	public void addInterceptors(InterceptorRegistry registry) {
		OpenSessionInViewInterceptor osivInterceptor = new OpenSessionInViewInterceptor(); 
		osivInterceptor.setSessionFactory(sessionFactory); 
		registry.addWebRequestInterceptor(osivInterceptor); 
	};*/

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		return viewResolver;
	}
	
	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties exceptionMappings = new Properties();
		exceptionMappings.put("java.lang.Exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");
		exceptionResolver.setExceptionMappings(exceptionMappings);
		Properties statusCodes = new Properties();
		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");
		exceptionResolver.setStatusCodes(statusCodes);
		return exceptionResolver;
	}
}
