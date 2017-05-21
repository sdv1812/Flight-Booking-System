package com.crossover.techtrial.java.se;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class TrialApplication extends SpringBootServletInitializer{
	private static final Logger logger = LoggerFactory.getLogger(TrialApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TrialApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(TrialApplication.class, args);
		logger.info("Main");
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder.create()
	    		.driverClassName("com.mysql.jdbc.Driver")
	    		.url("jdbc:mysql://localhost:3306/db")
	    		.username("root")
	    		.password("deepak2222")
	    		.build();
	    		
	}
	
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
	

}