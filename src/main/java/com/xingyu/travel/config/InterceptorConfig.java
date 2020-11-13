package com.xingyu.travel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.config
 * @Version: 1.0
 **/
// @Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private HandlerInterceptor initInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(initInterceptor).addPathPatterns("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceHandler请求路径
		//addResourceLocations 在项目中的资源路径
		//setCacheControl 设置静态资源缓存时间
		System.out.println("-------------------------");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		// WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}
