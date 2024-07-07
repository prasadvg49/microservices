package com.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication(scanBasePackages={"com.test"})
//@EnableDiscoveryClient
//@EnableEurekaClient

public class BootjpaApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(BootjpaApplication.class);
	
	
	public static void main(String[] args) {
		
		 logger.info("this is a info message");
	     logger.warn("this is a warn message");
	     logger.error("this is a error message");

		SpringApplication.run(BootjpaApplication.class, args);
	}
	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test"))
				.paths(PathSelectors.any())
				.build();
	}
	
	@Bean	
	public RestTemplate restTemplate()
	{
		
	return new RestTemplate();
		
	}
	
	
 	
//	@Bean
//	@LoadBalanced
/*	public RestTemplate restTemplate2()
	{
		
		return new RestTemplate();
	}*/
	

}
