package app.appmeteo.model;

public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
    @Override
    public String toString(){
        return
                "WEATHER: \n"+
                "id: "+ id+
                "\nmain: "+ main+
                "\ndescription: "+ description+
                "\nicon: " +icon;
    }
}
