package com.kbutz.templog.templog;

import com.kbutz.templog.templog.constants.Secrets;
import net.aksingh.owmjapis.core.OWM;
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

	public static void main(String[] args) {
		SpringApplication.run(TemplogApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public OWM owm() {
		OWM owm = new OWM(Secrets.OWM_API_KEY);
		owm.setUnit(OWM.Unit.IMPERIAL);
		return owm;
	}

}
