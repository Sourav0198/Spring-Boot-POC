package com.example.postgreespringrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EntityScan(basePackages = {"com.example.postgreespringrest.existEntity"})
//@EnableTransactionManagement
public class PostgreespringrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgreespringrestApplication.class, args);
	}

}
