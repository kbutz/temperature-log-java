package com.kbutz.templog.templog.controller;

import com.kbutz.templog.templog.data.TemperatureReading;
import com.kbutz.templog.templog.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
        return temperatureRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
    }

    @RequestMapping("/find-range/{from}/{to}")
    public List<TemperatureReading> findRange(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to) throws Exception {

        try {
            return temperatureRepository.findAllByTimeBetween(LocalDateTime.parse(from), LocalDateTime.parse(to));
        } catch (Exception e) {
            // TODO: refactor to a new exception class
            throw new Exception("Failed to find by range at: " + e.getClass() + e.getCause() + " " + e.getMessage(), e);
        }
    }

    /**
     * A convenience end-point to clear readings during development
     */
    @RequestMapping("/clear-all-readings")
    public void clear() {
        temperatureRepository.deleteAll();
    }
}
