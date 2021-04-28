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
        //String ss = s.Serelize();
        //Scooter v = (Scooter) s.deSerelize(ss);
        //System.out.println(v.getMaxSpeed());
        ArrayList<Vehicle> list = new ArrayList<>();
        LightVehicle vihicle = new LightVehicle("rapid - 2017", 240, true, 120, bodyType.sedan);
        vihicle.setFirm("Skoda");
        LightVehicle vihicle2 = new LightVehicle("rapid - 2020", 320, false, 220, bodyType.sedan);
        vihicle.setFirm("Skoda");
        list.add(vihicle);
        list.add(vihicle2);
        list.add(vihicle);
        list.add(s);


       // XMLList list1 = new XMLList();
       // list1.vihecles = list;
        //list1.vihecles.add(list.get(0));
       //list1.vihecles.add(list.get(1));

        //!!!!!!!!!todo  YAML
        try {
            //String s ="";

            //ObjectMapper om = new ObjectMapper(new YAMLFactory());
            //String sss = om.writeValueAsString(list);
            //om.writeValue(new File("C:\\tmp\\Vehicle.yaml"), list);
            //    System.out.println(sss);
            //om = null;
        } catch (Exception e) {
            System.out.println("hello");
            e.printStackTrace();

        }
        //todo Binary
        try {
            //ObjectMapper mapper = new ObjectMapper(new Bson);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(b);
            stream.close();
            stream.writeObject(list);
            System.out.println();
            ObjectInputStream stream2 = new ObjectInputStream(new ByteArrayInputStream(b.toByteArray()));
            ArrayList<Vehicle> vih = (ArrayList<Vehicle>) stream2.readObject();
            stream2.close();
            //System.out.println(vih.get(1).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //todo XML
        try {
            //JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
            XmlMapper m = new XmlMapper();

            //mapper.enable(SerializationFeature.INDENT_OUTPUT);
            //mapper.registerModule(jaxbAnnotationModule);
            //mapper.registerModule(new GuavaModule());


            m.writeValue(new File("C:\\tmp\\Vehicle2.xml"),list);
            //String out = map.writeValueAsString(new LightVehicle());
            //System.out.println(out);
            ArrayList ss = m.readValue(new File("C:\\tmp\\Vehicle2.xml"),ArrayList.class);
            System.out.println("fuckyou");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
