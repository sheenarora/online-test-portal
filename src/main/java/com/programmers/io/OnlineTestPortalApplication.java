
package com.programmers.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class OnlineTestPortalApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OnlineTestPortalApplication.class);
	
	public static void main(String[] args) { 
		SpringApplication.run(OnlineTestPortalApplication.class, args);
	}
}
