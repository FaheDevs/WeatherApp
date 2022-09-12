package app.appmeteo.model;

import java.util.List;

public class Daily {
    private long dt;
    private long sunrise;
    private long sunset;
    private Temp temp;
    private FeelsLike feels_like;
    private double pressure;
    private double humidity;
    private double dew_point;
    private double wind_speed;
    private double wind_deg;
    private List<Weather> weather;
    private double clouds;
    private double pop;
    private double rain;
    private double uvi;





    public long getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public double getClouds() {
        return clouds;
    }

    public double getPop() {
        return pop;
    }

    public double getRain() {
        return rain;
    }

    public double getUvi() {
        return uvi;
    }

    public Temp getTemp() {
        return temp;
    }

    @Override
    public String toString(){
        return
       "\nDAILY: "+
      "\nDate: "+ Utilities.stampToDate(dt,"UTC+00:00")+
      "\nSunrise: "+ Utilities.stampToDate(sunrise,"UTC+00:00")+
      "\nSunset: "+ Utilities.stampToDate(sunset,"UTC+00:00")+
      "\n"+ temp+
      "\n"+ feels_like+
      "\npressure: "+pressure+
      "\nhumidity: "+humidity+
      "\ndew point: "+dew_point+
      "\nwind speed: "+wind_speed+
      "\nwind deg: " + wind_deg+"\n"+
      weather+
      "\nclouds: "+clouds+
      "\npop: "+pop+
      "\nrain: "+rain+
      "\nuvi: "+uvi;

    }



}
