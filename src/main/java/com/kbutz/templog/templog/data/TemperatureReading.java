package com.kbutz.templog.templog.data;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class TemperatureReading {

    @Id
    private String Id;

    // FIXME: convert temps to Double
    private String insideTemp;
    private String outsideTemp;
    private Date time;

    // TODO: add additional fields for outside weather data? wind speed/direction, rain/snow?

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInsideTemp() {
        return insideTemp;
    }

    public void setInsideTemp(String insideTemp) {
        this.insideTemp = insideTemp;
    }

    public String getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(String outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TemperatureReading(String insideTemp, String outsideTemp, Date time) {
        this.insideTemp = insideTemp;
        this.outsideTemp = outsideTemp;
        this.time = time;
    }

    @Override
    public String toString() {
        return insideTemp
                + ", "
                + outsideTemp
                + ", "
                + time.toString();
    }
}
