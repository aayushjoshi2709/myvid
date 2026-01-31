package com.github.aayushjoshi2709.myvid.encodingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EncodingserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EncodingserviceApplication.class, args);
	}
}
