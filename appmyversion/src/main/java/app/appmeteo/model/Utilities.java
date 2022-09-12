package app.appmeteo.model;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


public class Utilities {

   public static String URL_API = "http://api.openweathermap.org/data/2.5/weather/";
   public static String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
   public static Client client = ClientBuilder.newClient();


    public static void main(String[] args)  {
        System.out.println(checkCityExisits("London"));
        System.out.println(getCityName(43.7001, -79.416));
        System.out.println(Arrays.toString(getCityCoord("Toronto")));

    }

    public static long setTimeToNoon(long timeStamp, String timeZone) throws ParseException {
        String tmpDate = stampToDate(timeStamp,timeZone);
        String ar[] = tmpDate.split(" ");
        String dateAtNoon = ar[0]+" 12:00";
        return dateToStamp(dateAtNoon,0,timeZone);
    }

    public static String getCityName(double lat, double lon){
        String jsonString = client.target(URL_API)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", API_KEY)
                .request(MediaType.APPLICATION_JSON).get(String.class);
        client.close();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        return ""+ map.get("name");
    }

    public static double[] getCityCoord(String cityName){

        String URL_API = "http://api.openweathermap.org/data/2.5/weather/";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .request(MediaType.APPLICATION_JSON).get(String.class);
        client.close();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        Map<String, Double> coord = (Map<String, Double >)map.get("coord");
        return new double[]{coord.get("lat"),coord.get("lon")};
    }
    public static boolean checkCityExisits(String city){
        Client client = ClientBuilder.newClient();
        Response reponse = client.target(URL_API).queryParam("q", city)
                .queryParam("appid", API_KEY).request().get();
        client.close();
        if(reponse.getStatus() == 200) return true;
        return false;
    }


    public static String stampToDate(long stamp, String timeZone){

        Date date = new Date(stamp*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZone)));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static long dateToStamp(String dateString, int daysBefore, String timeZone) throws ParseException {

        Date dateObject=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
        Date dateSubtracted = substractDays(dateObject, daysBefore, timeZone);
        long unixTime = dateSubtracted.getTime()/ 1000L;

        return unixTime;



    }

    public static Date substractDays(Date date, int days, String timeZone){

        LocalDateTime dt = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return Date.from( dt.minusDays(days).atZone(ZoneId.of(timeZone))
                .toInstant());
    }


}
