package com.kbutz.templog.templog.tasks;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbutz.templog.templog.data.RawTempDto;
import com.kbutz.templog.templog.data.TemperatureReading;
import com.kbutz.templog.templog.repository.TemperatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {

    @Autowired
    private TemperatureRepository temperatureRepository;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void persistCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();

        String tempRead = restTemplate.getForObject("http://192.168.1.5:5000/", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        RawTempDto b = null;
        try {
            b = objectMapper.readValue(tempRead, RawTempDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        log.info(b.getRawTemp());
        temperatureRepository.save(new TemperatureReading(b.getRawTemp(), new Date()));
//        log.info("This record has been added to the db: {}", temperatureRepository.findTopByOrderByTimeDesc().toString());
    }
}
