package com.kbutz.templog.templog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class TemplogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplogApplication.class, args);
	}

}
