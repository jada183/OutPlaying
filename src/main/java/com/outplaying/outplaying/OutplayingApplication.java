package com.outplaying.outplaying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan({"com.outplaying.controller", "com.outplaying.service.implementation"})
@EnableJpaRepositories("com.outplaying.repository") 
@EntityScan({"com.outplaying.model"})
public class OutplayingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutplayingApplication.class, args);
	}

}
