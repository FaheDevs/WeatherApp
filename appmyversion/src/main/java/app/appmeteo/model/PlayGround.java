package app.appmeteo.model;

import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlayGround {



    private String getLon (String city ){
        String URL_API = "http://api.openweathermap.org/data/2.5/weather/";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API).queryParam("q", city)
                .queryParam("appid", API_KEY).queryParam("units", "metric")
                .request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        Map<String, Object > coord = (Map<String, Object >)map.get("coord");
        return  coord.get("lon").toString();
    }

    private String getLat (String city ){
        String URL_API = "http://api.openweathermap.org/data/2.5/weather/";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API).queryParam("q", "london")
                .queryParam("appid", API_KEY).queryParam("units", "metric")
                .request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        Map<String, Object > coord = (Map<String, Object >)map.get("coord");
        return coord.get("lat").toString();
    }

    //get DT as Double
    private long getDate (String lon ,String lat ){
//        Map<String, Object > weather = new HashMap<>();

        String URL_API = "https://api.openweathermap.org/data/2.5/onecall?";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("exclude", "hourly,minutely,current")
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        List<Map> list =(List<Map>) map.get("daily");
//                for (int i=0;i<7;i++){
//                   if ( (long)list.get(i).get("dt")==stamp){
//                       weather = (Map<String, Object >)list.get(i);
//                   }
//                }

//        Map<String, Object > weather = (Map<String, Object >)list.get(0).get("dt");
        long weather =  Math.round( (double) list.get(0).get("dt"));

        return weather;



    }
    private Map<String, Object > searchOpen (String lon ,String lat,long stamp ){
        Map<String, Object > weather = new HashMap<>();

        String URL_API = "https://api.openweathermap.org/data/2.5/onecall?";
        String API_KEY = "c0a7b100bf22ca6b175765679d769e76";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(URL_API)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("exclude", "hourly,minutely,current")
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(jsonString, map.getClass());
        List<Map> list =(List<Map>) map.get("daily");
        for (int i=0;i<list.size();i++){
            if ( (long)  Math.round( (double)list.get(i).get("dt"))==stamp){
                weather = (Map<String, Object >)list.get(i);
            }
        }

//        Map<String, Object > weather = (Map<String, Object >)list.get(0).get("dt");
//        double weather = (double) list.get(0).get("dt");

        return weather;



    }


    public static void main(String[] args) {



        PlayGround test= new PlayGround();
        System.out.println(test.getLon("London"));
        System.out.println("test1: "+test.getDate(test.getLon("london"), test.getLat("london")));
        System.out.println(test.searchOpen(test.getLon("london"), test.getLat("london"), test.getDate(test.getLon("london"), test.getLat("london") ) ));




    }
}