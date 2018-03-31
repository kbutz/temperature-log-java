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

//	@Override
//	public void run(String... args) throws Exception {
//		temperatureRepository.deleteAll();
//
//		temperatureRepository.save(new TemperatureReading("70.5", new Date()));
//		temperatureRepository.save(new TemperatureReading("71", new Date()));
//
//		// printall
//		for (TemperatureReading t : temperatureRepository.findAll()) {
//			System.out.println(t);
//		}
//
//		// print latest
//		System.out.println(temperatureRepository.findTopByOrderByTimeDesc().toString());
//	}
}
