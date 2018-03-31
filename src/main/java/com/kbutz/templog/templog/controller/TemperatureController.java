package com.kbutz.templog.templog.controller;

import com.kbutz.templog.templog.data.TemperatureReading;
import com.kbutz.templog.templog.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TemperatureController {
    @Autowired
    private TemperatureRepository temperatureRepository;

    @RequestMapping("/current-reading")
    public TemperatureReading getCurrentTemperature() {
        return temperatureRepository.findTopByOrderByTimeDesc();
    }

    @RequestMapping("/all-readings")
    public List<TemperatureReading> listAllTemperatures() {
        return temperatureRepository.findAll();
    }

    /**
     * A convenience end-point to clear readings during development
     */
    @RequestMapping("/clear-all-readings")
    public void clear() {
        temperatureRepository.deleteAll();
    }
}
