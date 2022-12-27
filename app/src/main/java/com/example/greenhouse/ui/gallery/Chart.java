package com.example.greenhouse.ui.gallery;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chart {

    @SerializedName("data")
    @Expose
    private boolean success;

    @SerializedName("timestamps")
    @Expose
    private List<String> timestamps = null;

    @SerializedName("humidity")
    @Expose
    private List<Float> humidity = null;

    @SerializedName("temperature")
    @Expose
    private List<Float> temperature = null;

    @SerializedName("current")
    @Expose
    private List<Float> current = null;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<String> timestamps) {
        this.timestamps = timestamps;
    }

    public List<Float> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<Float> humidity) {
        this.humidity = humidity;
    }

    public List<Float> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Float> temperature) {
        this.temperature = temperature;
    }

    public List<Float> getCurrent() {
        return current;
    }

    public void setCurrent(List<Float> current) {
        this.current = current;
    }

}

