package com.rs.spring5.zuulproxy;

import com.rs.spring5.zuulproxy.filters.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulproxyApplication.class, args);
	}

	/*@Bean
	public CustomRouteFilter customRouteFilter(){
		return new CustomRouteFilter();
	}*/

	@Bean
	public RequestLoggerFilter requestLoggerFilter(){
		return new RequestLoggerFilter();
	}

	@Bean
	public CustomPreZuulFilter customPreZuulFilter(){
		return new CustomPreZuulFilter();
	}

	@Bean
	public CustomPreExtractZuulFilter customPreExtractZuulFilter(){
		return new CustomPreExtractZuulFilter();
	}

	@Bean
	public CustomPostZuulFilter customPostZuulFilter(){
		return new CustomPostZuulFilter();
	}
}
