package com.kbutz.templog.templog.data

import groovy.transform.CompileStatic

import java.time.LocalDateTime

@CompileStatic
class TemperatureReading {

    String type
    LocalDateTime time

    List<TemperatureData> temperatureData

    TemperatureReading(String type, LocalDateTime time, List<TemperatureData> temperatureData) {
        this.type = type
        this.time = time
        this.temperatureData = temperatureData
    }
}
