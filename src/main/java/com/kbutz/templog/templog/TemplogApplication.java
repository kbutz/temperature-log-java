package com.kbutz.templog.templog;

import net.aksingh.owmjapis.core.OWM;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class TemplogApplication {

	@Value("${owm.api.key}")
	private String OWM_API_KEY;

	public static void main(String[] args) {
		SpringApplication.run(TemplogApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public OWM owm() {
		OWM owm = new OWM(OWM_API_KEY);
		owm.setUnit(OWM.Unit.IMPERIAL);
		return owm;
	}

}
