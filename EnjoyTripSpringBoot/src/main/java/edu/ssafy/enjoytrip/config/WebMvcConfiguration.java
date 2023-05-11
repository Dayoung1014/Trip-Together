package edu.ssafy.enjoytrip.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.ssafy.enjoytrip.interceptor.ConfirmInterceptor;
 

@Configuration
@MapperScan(basePackages = {"edu.ssafy.enjoytrip.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final List<String> patterns = Arrays.asList("/article/*", "/admin", "/user/list");
	
	private ConfirmInterceptor confirmInterceptor;
	
	public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
		this.confirmInterceptor = confirmInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirmInterceptor)
				.addPathPatterns(patterns);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
	}


	
	
	
}
