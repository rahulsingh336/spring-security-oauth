package com.rs.spring.balanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BalanceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceserviceApplication.class, args);
	}
}

@RestController
class ApplicationController{

	// API - read
	@PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
	@GetMapping
	public String  getBalance(){
		return "336";
	}

	// API - write
	@PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('write')")
		@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String  updateBalance(){
		return "336-updatedbalance";
	}
}
