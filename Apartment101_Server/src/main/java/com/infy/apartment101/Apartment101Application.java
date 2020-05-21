package com.infy.apartment101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class Apartment101Application {

	public static void main(String[] args) {
		SpringApplication.run(Apartment101Application.class, args);
	}

}
