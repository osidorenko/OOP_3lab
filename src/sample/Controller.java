package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.fxml.FXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Controller {




    //@FXML

    public void go() {
        //ArrayList<Vehicle> aa = listtt;

        //s.
        for (int i = 0; i < listtt.size(); i++) {
            //listtt.get(0)
            Vehicle v = (Vehicle) listtt.get(i);
            System.out.println(v.toString());
        }


    }

    public void exit() {
        System.exit(3);
    }

    @FXML
    private Button bb;
    ArrayList listtt;

    public void test1() {
        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        list = deSerialYAML();

        list1 = (ArrayList) deSerialXML();
        listtt = list1;

        //System.out.println(v.getPassengers());
    }

    String temp = "";

    public void test() {
        List list = new ArrayList<Vehicle>();
        LightVehicle vihicle = new LightVehicle("rapid - 2017", 240, true, 120, bodyType.sedan);
        FreightVehicle f = new FreightVehicle("same", "BMW", 120, 2, new Cargo(cargoType.platform, 8, 40), 50);
        vihicle.setFirm("Skoda");
        LightVehicle vihicle2 = new LightVehicle("MPV", 260, false, 220, bodyType.hatchback);
        Motocycle moto = new Motocycle("R1", 560);
        moto.setFirm("Yamaha");
        moto.setMaxSpeed(280);
        Motocycle moto2 = new Motocycle("something", 1000);
        moto2.setFirm("BMW");
        moto2.setMaxSpeed(310);
        vihicle2.setFirm("Mazda");
        Scooter s = new Scooter("Xiaomi", 30);
        s.setMaxSpeed(35);

        //list.add(s);
        list.add(vihicle);
        list.add(vihicle2);
        list.add(vihicle);
        //list.add(f);
        //list.add(f);
        //list.add(f);
        //list.add(moto);
        //list.add(moto2);
        temp = SerialXML(list);
        //SerialBinary(list);
        //SerialYAML(list);
    }

    private ByteArrayOutputStream SerialBinary(List list) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(b);
            stream.close();
            stream.writeObject(list);
            //FileReader freader = new FileReader("C:\\tmp\\Vehicle.yaml");
            //File f = new File("C:\\tmp\\Vehicle1.txt");
            FileWriter fwriter = new FileWriter(new File("C:\\tmp\\Vehicle1.txt"), false);
            fwriter.write(b.toString());
            fwriter.close();
            return b;
        } catch (Exception e) {
            System.err.println("ERROR : serialization binary");
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Vehicle> deSerialBinary(ByteArrayOutputStream b) {
        try {
            ObjectInputStream stream2 = new ObjectInputStream(new ByteArrayInputStream(b.toByteArray()));
            ArrayList<Vehicle> vih = (ArrayList<Vehicle>) stream2.readObject();

            stream2.close();
            return vih;
        } catch (Exception e) {
            System.err.println("ERROR : deserialization binary");
            e.printStackTrace();
            return null;
        }
    }

    private String SerialYAML(List list) {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            String sss = om.writeValueAsString(list);
            om.writeValue(new File("C:\\tmp\\Vehicle.yaml"), list);
            return sss;
        } catch (Exception e) {
            System.err.println("ERROR : serialization yaml");
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Vehicle> deSerialYAML() {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
//            ArrayList list = om.readValue(new File("C:\\tmp\\Vehicle.yaml"), new TypeReference<ArrayList<Vehicle>>(){});
            ArrayList list = om.readValue(new File("C:\\tmp\\Vehicle.yaml"), ArrayList.class);
            return list;
        } catch (Exception e) {
            System.err.println("ERROR : deserialization yaml");
            e.printStackTrace();
            return null;
        }
    }

    private String SerialXML(List list) {
        try {


            //ObjectMapper m = new ObjectMapper(new XmlFactory());
            //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //m.writeValue(byteArrayOutputStream, l);
            //assertEquals(XML, byteArrayOutputStream.toString());
            XmlMapper m = new XmlMapper();
            String result = m.writeValueAsString(list);
            m.writeValue(new File("C:\\tmp\\Vehicle2.xml"), list);
            System.out.println(result);
            return result;
        } catch (Exception ex) {
            System.err.println("ERROR : serialization xml");
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private List<Vehicle> deSerialXML() {
        try {

            XmlMapper m = new XmlMapper();
            //ObjectMapper m = new ObjectMapper(new XmlFactory());
            List list = m.readValue(new File("C:\\tmp\\Vehicle2.xml"), new TypeReference<List<Vehicle>>() {
            });
            return list;

        } catch (Exception e) {
            System.err.println("ERROR : deserialization xml");
            e.printStackTrace();
            return null;
        }
    }
}
