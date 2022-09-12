package app.appmeteo.model;

import java.util.List;

public class Current {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    private double feels_like;
    private double pressure;
    private double humidity;
    private double dew_point;
    private double wind_speed;
    private double wind_deg;
    private List<Weather> weather;
    private double clouds;
    private double uvi;
    private double visibility;

    public long getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getDew_point() {
        return dew_point;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_deg() {
        return wind_deg;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public double getClouds() {
        return clouds;
    }



    public double getUvi() {
        return uvi;
    }

    public double getVisibility() {
        return visibility;
    }

    @Override
    public String toString() {
        return "Weather on "+ Utilities.stampToDate(dt,"UTC")+
                "\nTemp: "+ temp+"\n"+
                weather;

    }
}
