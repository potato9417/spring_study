package com.javateam.myBatisTransactionSample.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Annotation configuration
 * 
 * @author javateam
 *
 */
@EnableWebMvc
@ComponentScan(basePackages = "com.javateam.myBatisTransactionSample.**")
//@ComponentScan(basePackages = {"com.javateam.myBatisTransactionSample.controller",
//							   "com.javateam.myBatisTransactionSample.dao",
//							   "com.javateam.myBatisTransactionSample.service.**"})
@Configuration
// public class WebConfig extends WebMvcConfigurerAdapter { // deprecated since spring 5
public class WebConfig implements WebMvcConfigurer {
				    // extends WebMvcConfigurationSupport { 
	
	/*
     * Configure ContentNegotiationManager
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    	
        configurer.ignoreAcceptHeader(true)
        		  .defaultContentType(MediaType.TEXT_HTML);
    }
	
    // 기존의 servlet-context.xml에 있던 InternalResourceViewResolver
	@Bean
	public InternalResourceViewResolver jspResolver() {
		
		InternalResourceViewResolver viewResolver 
			= new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1); // priority(우선순위)
		
		return viewResolver;
	} //
	
	// 여러가지 View단의 미디어를 수용하는 ContentNegotiatingViewResolver
	@Bean
	public ContentNegotiatingViewResolver setContentViewResolver() {
		
		ContentNegotiatingViewResolver contentViewResolver
			 = new ContentNegotiatingViewResolver();
				
		contentViewResolver.setOrder(0); // priority(우선순위)
		
		// various ViewResolvers setting
		List<ViewResolver> listViewResolver = new ArrayList<>();
		listViewResolver.add(new BeanNameViewResolver());
		listViewResolver.add(this.jspResolver());
		
		contentViewResolver.setViewResolvers(listViewResolver);
		
		List<View> defaultViews = new ArrayList<>();
		
		MappingJackson2JsonView mappingJackson2JsonView 
			= new MappingJackson2JsonView();
		mappingJackson2JsonView.setPrettyPrint(true);
		
		defaultViews.add(mappingJackson2JsonView);
		
		contentViewResolver.setDefaultViews(defaultViews);
		
		return contentViewResolver;
	} //

}
