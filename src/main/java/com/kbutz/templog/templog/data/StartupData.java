package com.kbutz.templog.templog.data;

import com.kbutz.templog.templog.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class StartupData {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        LocalDateTime date = LocalDateTime.of(2018, 1, 1, 6, 0);


//        temperatureRepository.save(new TemperatureReading(
//                "65.0",
//                "23.0",
//                date));
    }
}
