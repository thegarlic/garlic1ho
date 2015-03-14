package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringMVCContext.class })
public class ApplicationContext {

}
