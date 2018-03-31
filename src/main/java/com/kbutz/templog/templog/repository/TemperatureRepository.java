package com.kbutz.templog.templog.repository;

import com.kbutz.templog.templog.data.TemperatureReading;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TemperatureRepository extends MongoRepository<TemperatureReading, String> {

    /**
     * Repository method to return the latest reading
     * @return latest temperature reading
     */
    TemperatureReading findTopByOrderByTimeDesc();

}
