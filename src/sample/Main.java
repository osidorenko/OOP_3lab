package sample;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        Scooter s = new Scooter("Xiaomi", 30);
        s.setMaxSpeed(35);
        ArrayList<Vehicle> list = new ArrayList<>();
        LightVehicle vihicle = new LightVehicle("rapid - 2017", 240, true, 120, bodyType.sedan);
        vihicle.setFirm("Skoda");
        LightVehicle vihicle2 = new LightVehicle("rapid - 2020", 320, false, 220, bodyType.sedan);
        vihicle.setFirm("Skoda");
        list.add(vihicle);
        list.add(vihicle2);
        list.add(vihicle);
        list.add(s);


    }
}
