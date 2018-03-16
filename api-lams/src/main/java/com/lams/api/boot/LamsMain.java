package com.lams.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = { "com.lams" })
@EnableAsync
@EnableScheduling
public class LamsMain {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LamsMain.class, args);
	}
}
