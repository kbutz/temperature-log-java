package com.kbutz.templog.templog.data;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class TemperatureReading {

    @Id
    private String Id;

    private String temperature;
    private Date time;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TemperatureReading(String temperature, Date time) {
        this.temperature = temperature;
        this.time = time;
    }

    @Override
    public String toString() {
        return temperature + " " + time.toString();
    }
}
