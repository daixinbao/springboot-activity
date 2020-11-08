package com.springboot.springboot;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class SpringbootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringbootApplication.class, args);
		SpringApplication app = new SpringApplication(SpringbootApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

}
