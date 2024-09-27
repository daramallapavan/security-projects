package com.security.securitywithoutjwt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SecurityWithoutJwtApplication{

	public static void main(String[] args) {
		SpringApplication.run(SecurityWithoutJwtApplication.class, args);
	}

}
