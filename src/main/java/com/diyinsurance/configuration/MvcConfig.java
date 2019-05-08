package com.diyinsurance.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
// tags this class as a source of bean definitions for application context.
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("Login");
		registry.addViewController("/article").setViewName("ViewAllArticles");
		registry.addViewController("/403").setViewName("403");
		registry.addViewController("/failLogin").setViewName("failLogin");
		registry.addViewController("/home").setViewName("Home");
		registry.addViewController("/test").setViewName("test");

	}

	// may need this for form base configuration in spring security

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/static/**").addResourceLocations("/static/");
	}

}
