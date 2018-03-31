package com.kbutz.templog.templog.data;

/**
 * A Dto for the raw readings from the Raspberry Pi
 */
public class RawTempDto {
    private String rawTemp;

    public String getRawTemp() {
        return rawTemp;
    }

    public void setRawTemp(String rawTemp) {
        this.rawTemp = rawTemp;
    }
}
