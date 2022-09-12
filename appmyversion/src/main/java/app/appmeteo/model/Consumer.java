package app.appmeteo.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Consumer {

    public Consumer() throws ParseException {
    }

    public City getCityDatata(String cityName) throws ParseException {
     double[] cityCoord = Utilities.getCityCoord(cityName);
        Gson gs = new Gson();
        City city = gs.fromJson(getOneCall(cityCoord[0],cityCoord[1]), City.class);
        city.setPreviousDays(getPreviousDays(cityCoord[0],cityCoord[1], city.getTimezone()));
        return city;
    }

    public static List<PreviousDay> getPreviousDays(double lat, double lon,String timeZone) throws ParseException {
        long stampsArray[] = new long[5];
        List<PreviousDay> previousDaysList = new ArrayList<>();

        long timestamp = Utilities.setTimeToNoon(new Date().getTime()/1000, timeZone);





        for(int i = 0; i < 5; i++){
            timestamp -= 86400;
            stampsArray[i] = timestamp;
        }

        String jsonString = "";
        for(long i : stampsArray){

            PreviousDay tmpDay = jsonToDay(getTimeMachine(lat, lon, i, timeZone));
            previousDaysList.add(tmpDay);

        }









        return  previousDaysList;
    }

    public static String getOneCall(double lat, double lon){
        String URL_API = "https://api.openweathermap.org/data/2.5/onecall?";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("units", "metric")
                .queryParam("exclude", "hourly")
                .queryParam("appid", API_KEY)
                .request(MediaType.APPLICATION_JSON).get(String.class);
        client.close();

        return jsonString;

    }

    public static String getTimeMachine(double lat, double lon, long timeStamp, String timeZone){


        String URL_API = "https://api.openweathermap.org/data/2.5/onecall/timemachine?";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("dt", timeStamp)
                .queryParam("exclude","hourly")
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .request(MediaType.APPLICATION_JSON).get(String.class);

        client.close();
        return jsonString;
    }

    public static PreviousDay jsonToDay(String jsonString){

        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        Map<String, Object> current = (Map<String, Object >)map.get("current");
        JsonElement jsonElement = gson.toJsonTree(current);
        PreviousDay previousDay = gson.fromJson(jsonElement, PreviousDay.class);


        return previousDay;
    }


    public static void main(String[] args) throws ParseException {
        Consumer con = new Consumer();
        City c = con.getCityDatata("London");
        System.out.println(c.getCityName());

    }



//    private Map<String, Object > getDataCurrent(String city){
//        String URL_API = "http://api.openweathermap.org/data/2.5/weather/";
//        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
//        Client client = ClientBuilder.newClient();
//        String jsonString = client.target(URL_API).queryParam("q", city)
//                .queryParam("appid", API_KEY).queryParam("units", "metric")
//                .request(MediaType.APPLICATION_JSON).get(String.class);
//        Gson gson = new Gson();
//        Map<String,Object> map = new HashMap<String,Object>();
//        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
//        return map;
//    }
//
//    //gets current , hourly , daily
//    private Map<String, Object > getDataOneCall(String lon ,String lat){
//        String URL_API = "https://api.openweathermap.org/data/2.5/onecall?";
//        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
//        Client client = ClientBuilder.newClient();
//        String jsonString = client.target(URL_API)
//                .queryParam("lon", lon)
//                .queryParam("lat", lat)
//                .queryParam("exclude", "minutely")
//                .queryParam("units", "metric")
//                .queryParam("appid", API_KEY)
//                .request(MediaType.APPLICATION_JSON).get(String.class);
//        Gson gson = new Gson();
//        Map<String,Object> map = new HashMap<String,Object>();
//        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
//        return map ;
//    }
//
//    private String getLat (String city ){
//        Map<String, Object > coord = (Map<String, Object >)getDataCurrent(city).get("coord");
//        return coord.get("lat").toString();
//
//    }
//    // they should be used directly in methods calling  arguments
//    private String getLon (String city ){
//        Map<String, Object > coord = (Map<String, Object >)getDataCurrent(city).get("coord");
//        return coord.get("lon").toString();
//    }
//    /*
//    * returns the current weather of the city from the one call api
//    * here we need to use the methods getlon and getlat as arguments
//    * so we can directly put the city */
//    private Map<String, Object > getCurrentOneCall (String lon ,String lat ){
//        Map<String, Object > current = (Map<String, Object>) getDataOneCall(lon,lat).get("current");
//        return current;
//    }
//    /*
//    * this will only return the list of hours that we gonna display :
//    * for now i let it just like this */
//    private List<Map> getHourly (String lon ,String lat){
//        List<Map> list =(List<Map>) getDataOneCall(lon,lat).get("hourly");
////        Map<String, Object > weather = (Map<String, Object >)list.get(0);
//        return list;
//    }
//
//
//
//    /* ------------------ALL THIS PART IS FOR THE CONVERSION FROM DATE TO STAMP -------------------------------------*/
//
//    public  long dateToStamp(String dateString, int daysBefore) throws ParseException {
//
//        Date dateObject=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
//        Date dateSubtracted = substractDays(dateObject, daysBefore);
//        long unixTime = dateSubtracted.getTime()/ 1000L;
//
//        return unixTime;
//
//
//
//    }
//    public  Date substractDays(Date date, int days){
//
//        LocalDateTime dt = date.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDateTime();
//
//        return java.util.Date.from( dt.minusDays(days).atZone(ZoneId.systemDefault())
//                .toInstant());
//    }
//
//
//    /*-------------------------------------------------------------------------------------------------------------------*/
//
//    /*
//    * this method right here gets the 7 days forecast
//    * needs some help */
//    private Map<String, Object > getDaily(String lon ,String lat,String yyyyMMdd) throws ParseException {
//        Map<String, Object > weather = new HashMap<>();
//        long dateStamp = dateToStamp(yyyyMMdd,0);
//
//        List<Map> list =(List<Map>) getDataOneCall(lon,lat).get("daily");
//        for (int i=0;i<list.size();i++){
//            if ( ((long)  Math.round( (double)list.get(i).get("dt"))==dateStamp)){
//                weather = (Map<String, Object >)list.get(i);
//            }
//        }
//        return weather;
//    }
//
//
//
//    /*
//    * this method checks the city
//    * */
//    public boolean checkCity(String city){
//        String URL_API = "http://api.openweathermap.org/data/2.5/weather/";
//        String  API_KEY = "c0a7b100bf22ca6b175765679d769e76";
//
//        Client client = ClientBuilder.newClient();
//        Response reponse = client.target(URL_API).queryParam("q", city)
//                .queryParam("appid", API_KEY).request().get();
//
//        if(reponse.getStatus() == 200) return true;
//        return false;
//    }
//









}

