package com.FetchRewards.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.FetchRewards.demo.Service.InitializerService;

@SpringBootApplication
public class DemoApplication {

	
	@Autowired
	InitializerService initializerService;
	
	@PostConstruct
	public void init() {
		initializerService.populateInitialData();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
