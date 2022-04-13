package com.example.weather.model;

public class WeeklyForecast {
    private String icon;

    private String description;

    private String lat; // max

    private String lon; // min


    public WeeklyForecast(String icon, String description, String lat, String lon) {
        this.icon = icon;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "WeeklyForecast{" +
                "icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
