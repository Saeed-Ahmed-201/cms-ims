package com.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ims")
public class ImsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ImsApplication.class, args);
	}

}
