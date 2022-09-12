package app.appmeteo.controller;

import app.appmeteo.AppMeteo;
import app.appmeteo.model.City;
import app.appmeteo.model.Consumer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class AppMeteoController implements Initializable {


    @FXML private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;
    @FXML private Label label;
    @FXML private Text closeButton;
    @FXML private Button settingsButton;
    @FXML private Button backToMainBtn;
    @FXML private Button addCityBtn;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox comboBox = new ComboBox();
    @FXML private ImageView weatherIcon;
    private int tmpcounter = 0;


    @FXML private GridPane settingsScene;
    @FXML private GridPane mainScene;
    @FXML private ListView<String> citiesList = new ListView<>();
    @FXML private VBox citiesBox;
    @FXML private TextField cityInput;
    
    private String[] iconsAr = {"01d","02d","03d","04d","09d","10d","11d","13d","50d",
                                "01n","02n","03n","04n","09n","10n","11n","13n","50n"};

    Consumer consumer = new Consumer();

    Map<String, City > citiesMap;
    List<String> defaultCities = new ArrayList<>();

    public AppMeteoController() throws ParseException {
    }
 @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        Font.loadFont(AppMeteo.class.getResource("/app/appmeteo/view/fonts/Helvetica.ttf").toExternalForm(), 10);

//     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//     datePicker.setValue(LocalDate.parse(formatter.format(new Date())));
     datePicker.setValue(LocalDate.now());



     defaultCities.add("Batna");
        defaultCities.add("Toronto");
        defaultCities.add("Paris");
        reloadCitiesList();

//        list to show at the beginning of the game

//
//        Thread taskThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                citiesMap = new HashMap<>();
//                try {
//                    citiesMap.put("Batna",consumer.getCityDatata("Batna"));
//                    citiesMap.put("London",consumer.getCityDatata("London"));
//                    citiesMap.put("Riyadh",consumer.getCityDatata("Riyadh"));
//                    citiesMap.put("Toronto",consumer.getCityDatata("Toronto"));
//                    citiesMap.put("Paris",consumer.getCityDatata("Paris"));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                    Platform.runLater(() -> reloadCitiesList());
//                }
//
//        });
//
//        taskThread.start();



    }


    @FXML
    private void closeProgram(){
        Platform.exit();
        System.exit(0);

    }

    @FXML
    private void toggleScenes(){
        mainScene.setVisible(!mainScene.isVisible());
        settingsScene.setVisible(!settingsScene.isVisible());
    }

    @FXML
    private void reloadCitiesList(){

        citiesBox.getChildren().clear();

        for(String cityName : defaultCities){

            citiesBox.getChildren().add(createCityContainer(cityName));


        }
        comboBox.getItems().clear();
        comboBox.getItems().addAll(defaultCities);
        comboBox.getSelectionModel().selectFirst();


    }


    @FXML
    private void addCity(){
        String cityName = cityInput.getText();
        cityInput.setText("");
        defaultCities.add(cityName);
       reloadCitiesList();






    }

    @FXML
    private void addCityByEnter(KeyEvent key){

            if (key.getCode().equals(KeyCode.ENTER))
            {
               addCity();
            }
    }


    private HBox createCityContainer(String cityName){
        Text newCity = new Text(cityName);
        HBox cityContainer = new HBox();
        Button btn = new Button();
        btn.setText("DELETE");
        btn.setOnAction(event -> {
            defaultCities.remove(cityName);
            citiesBox.getChildren().remove(cityContainer);
            System.out.println(citiesBox.getChildren().size());
        });
        cityContainer.getChildren().addAll(newCity,btn);
        return cityContainer;
    }

    // this method for testing only. Removed if production version
    // you just need to click on the screen to invoke this method
    @FXML
    private void changeIcon(){
        weatherIcon.setImage(
                new Image("http://openweathermap.org/img/wn/"+iconsAr[tmpcounter++%17]+"@2x.png"));
    }
}

