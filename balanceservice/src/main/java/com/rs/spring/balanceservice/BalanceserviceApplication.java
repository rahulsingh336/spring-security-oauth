package com.rs.spring.balanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BalanceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceserviceApplication.class, args);
	}
}

@RestController
class ApplicationController{

	@GetMapping
	public String  getBalance(){
		return "336";
	}
}
