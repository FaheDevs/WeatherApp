package app.appmeteo.model;

public class FeelsLike {
    private double day;
    private double night;
    private double eve;
    private double morn;

    public double getDay() {
        return day;
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
                "FEELS LIKE: \n"+
                        "day: "+ day+
                        "\nnight: "+ night+
                        "\neve: "+ eve+
                        "\nmorn: " +morn;
    }
}
