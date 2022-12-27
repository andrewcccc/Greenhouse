package com.example.greenhouse.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Details {
    @SerializedName("temperature")
    @Expose
    private String Temperature;

    @SerializedName("humidity")
    @Expose
    private String Humidity;


    @SerializedName("heater")
    @Expose
    private String heater;

    @SerializedName("humidifier")
    @Expose
    private String humidifier;


    @SerializedName("timestamps")
    @Expose
    private String Timestamps;

    @SerializedName("current")
    @Expose
    private Float current;

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }


    public String getHeater() {
        return heater;
    }

    public void setHeater(String heater) {
        heater = heater;
    }


    public String getHumidifier() {
        return heater;
    }

    public void setHumidifier(String humidifer) {
        humidifer = humidifier;
    }


    public String getHumidity() {
        return Humidity;
    }
    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getTimestamps() {
        return Timestamps;
    }

    public void setTimestamps(String timestamps) {
        Timestamps = timestamps;
    }



    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }
}


