package com.kbutz.templog.templog.tasks;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbutz.templog.templog.constants.Secrets;
import com.kbutz.templog.templog.data.RawTempDto;
import com.kbutz.templog.templog.data.TemperatureReading;
import com.kbutz.templog.templog.repository.TemperatureRepository;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OWM owm;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TemperatureRepository temperatureRepository;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void persistCurrentTime() {

        String insideTemp = getInsideTempFromPi();
        String outsideTemp = getOutsideTempFromOwmApi();

        temperatureRepository.save(new TemperatureReading(insideTemp,
                                                          outsideTemp,
                                                          LocalDateTime.now()));
    }

    private String getInsideTempFromPi() {
        String tempRead = restTemplate.getForObject(Secrets.PI_URI, String.class);

        RawTempDto b = null;
        try {
            b = objectMapper.readValue(tempRead, RawTempDto.class);
        } catch (IOException e) {
            log.error(e.toString());
        }

        return b != null ? b.getRawTemp() : "0.00";
    }

    private String getOutsideTempFromOwmApi() {
        CurrentWeather currentWeather = null;
        try {
            currentWeather = owm.currentWeatherByCityName(Secrets.STATE);
        } catch (APIException e) {
            log.error(e.toString());
        }

        return currentWeather != null ? currentWeather.getMainData().getTemp().toString() : "0.00";
    }
}
