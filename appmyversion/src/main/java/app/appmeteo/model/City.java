package app.appmeteo.model;

import java.util.List;

public class City {

    private double lat;
    private double lon;
    private Current current;
    private String timezone;
    private List<Daily> daily;
    private List<PreviousDay> previousDays;

    public String getCityName(){
        return Utilities.getCityName(lat,lon);
    }
    public Current getCurrent() {
        return current;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public List<PreviousDay> getPreviousDays() {
        return previousDays;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public void setPreviousDays(List<PreviousDay> previousDays) {
        this.previousDays = previousDays;
    }

    @Override
    public String toString(){
        return
                "Weather Data for "+ Utilities.getCityName(lat,lon)+

                "\nlat:"+ lat+
                "\nlon: "+lon+
                "\nTime Zone: "+timezone+"\n"+
                daily.toString()+
                previousDays.toString();





}

}
