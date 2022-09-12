package app.appmeteo.model;

public class Temp {

    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;



    public double getDay() {
        return day;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }

    @Override
    public String toString(){
        return
                "TEMP: \n"+
                        "day: "+ day+
                        "\nmin: "+ min+
                        "\nmax: "+ max+
                        "\nnight: "+ night+
                        "\neve: "+ eve+
                        "\nmorn: " +morn;
    }


}
