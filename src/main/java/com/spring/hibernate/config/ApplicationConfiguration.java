package com.spring.hibernate.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.spring.hibernate" }, useDefaultFilters = false, excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApplicationConfiguration.class) }, includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
Controller.class, Component.class }) })
public class ApplicationConfiguration {

}
