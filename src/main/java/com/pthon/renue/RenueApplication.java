package com.pthon.renue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenueApplication {

	public static String[] ARGS;
	public static void main(String[] args) {
		ARGS = args;
		SpringApplication.run(RenueApplication.class, args);
	}


}
