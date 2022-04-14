package com.example.weather.model;

public class ForecastSetting {

    private String  cityName;
    private String numberDays;


    public ForecastSetting() {
    }

    public ForecastSetting(String cityName, String numberDays) {
        this.cityName = cityName;
        this.numberDays = numberDays;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(String numberDays) {
        this.numberDays = numberDays;
    }

    @Override
    public String toString() {
        return "ForecastSetting{" +
                "cityName='" + cityName + '\'' +
                ", numberDays='" + numberDays + '\'' +
                '}';
    }
}
