package gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import processes.*;
import vehicles.*;


import java.awt.Button;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Controller {
    //MAIN LIST OF VEHICLES
    ArrayList vehicles = new ArrayList();

    //MAIN METHODS CONNECTED WITH PROCESSING DATA
    public String getPath() {
        String s = pathfield.getText();
        File f = new File(s);
        if (!f.isFile()) {
            statefield.setText("Файл не существует");
            return "";
        }
        return s;
    }

    private void getList(ArrayList<LinkedHashMap> list) {
        if (list == null) {
            return;
        }
        vehicles.clear();
        try {
            for (int i = 0; i < list.size(); i++) {
                vehicleType t = vehicleType.valueOf(list.get(i).get("vtype").toString());
                if (t.equals(vehicleType.lightvehicle)) {
                    vehicles.add(new LightVehicle(list.get(i)));
                } else {
                    if (t.equals(vehicleType.freightveicle)) {
                        vehicles.add(new FreightVehicle(list.get(i)));
                    } else {
                        if (t.equals(vehicleType.electriccar)) {
                            vehicles.add(new ElectricCar(list.get(i)));
                        } else {
                            if (t.equals(vehicleType.motocycle)) {
                                vehicles.add(new Motocycle(list.get(i)));
                            } else {
                                if (t.equals(vehicleType.scooter)) {
                                    vehicles.add(new Scooter(list.get(i)));
                                } else {
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            statefield.setText("Не известное содержимое");
        }
      //  Switch s = new Switch(new Serialization(new Process(new EncryptionAdapter()),vehicles),new DeSerialialization(new Process(new JsonProcess()),""));

    }

    public void loadList(int i, String path) {
        if (path.equals("")) {
            return;
        }


        ArrayList list = new ArrayList();
        switch (i) {
            case 0: {
                list = deSerialBinary(path);
                if (list != null) {
                    vehicles = list;
                    statefield.setText("Данные Binary загружены ");
                }
                return;
            }
            case 1: {
                list = deSerialXML(path);
                if (list != null) {
                    getList(list);
                    statefield.setText("Данные XML загружены ");
                }
                return;
            }
            case 2: {
                list = deSerialYAML(path);
                if (list != null) {
                    getList(list);
                    statefield.setText("Данные YAML загружены ");
                }
                return;
            }
            case 3: {
                list = getJson(path);
                if (list != null) {
                    getList(list);
                    statefield.setText("Данные JSON загружены ");
                }
                return;
            }
            case 4: {

                ReadZip(path);
                statefield.setText("Данные из архива загружены ");
                return;
            }
            case 5: {
                EnCryptData(path);
                if (vehicles.size() == 0) {
                    statefield.setText("Из расшифрованных данных ничего не понятно ");
                } else
                    statefield.setText("Данные расшифрованы ");
                return;
            }
        }
    }

    public void auto() {
        vehicles.clear();
        z++;
        String path = getPath();
        if (path.equals("")) {
            return;
        }
        int len = path.length();
        if (path.substring(len - 4, len).equals("json")) {
            loadList(3, path);
        } else {
            if (path.substring(len - 3, len).equals("xml")) {
                loadList(1, path);
            } else {
                if (path.substring(len - 3, len).equals("zip")) {
                    loadList(4, path);
                } else {
                    if (path.substring(len - 4, len).equals("yaml")) {
                        loadList(2, path);
                    } else {
                        if (path.substring(len - 3, len).equals("txt")) {
                            loadList(0, path);
                            if (vehicles.size() == 0) {
                                loadList(5, path);
                            }
                        }
                    }
                }
            }
        }
        if (vehicles.size() == 0) {
            for (int i = 0; i < 6; i++) {
                loadList(i, path);
                if (vehicles.size() != 0) {
                    return;
                }
            }
            statefield.setText("Не известный формат файла");
        }

    }


    //DATA PROCESSING !!!
    public void CrytData() {
        try (FileWriter wr = new FileWriter("C:\\tmp\\Crypt.txt", false)) {
            if (vehicles.size() == 0) {
                statefield.setText("отсутствуют записываемые данные");
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(vehicles);
            Encryption e = new Encryption(data);
            e.setCrypt(5);
            e.write();
            String writetofile = e.getEncrypted();
            System.out.println(writetofile);

            wr.write(writetofile);
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EnCryptData(String path) {
        try {
            Encryption e = new Encryption();
            String ret = Files.readAllLines(new File(path).toPath()).get(0);
            e.setEncrypted(ret);
            String s = e.getCrypted(5);
            ObjectMapper mapper = new ObjectMapper();
            ArrayList list = mapper.readValue(s, ArrayList.class);
            getList(list);

        } catch (Exception e) {
            statefield.setText("Файл не того формата");
        }
    }

    public void CreateZip() {

    }

    public void ReadZip(String path) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name = "";
            long size;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream("C:\\tmp\\A" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            ArrayList list = new ArrayList();
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(new File("C:\\tmp\\A" + name), ArrayList.class);
            getList(list);
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
            statefield.setText("Файл не того формата");
        }
    }


    //SERIALIZATION AND DESERIALIZATION
    public void XMLandJSON() {
        ArrayList list = new ArrayList();
        list = deSerialXML("C:\\tmp\\Vehicle2.xml");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("C:\\tmp\\Vehicle3.json"), list);
            System.out.println(mapper.writeValueAsString(list));

            statefield.setText("Данные трасформированны и записаны в файл");
        } catch (Exception e) {
            //e.printStackTrace();
            statefield.setText("Файл не того формата");
        }
    }

    JsonProcess json = new JsonProcess();

    public void SerialJson(List list) {
        json.Serialization(list);
    }

    public ArrayList getJson(String path) {

        return json.deSerialization(path);
    }

    private void SerialBinary(List list) {
        binary.Serialization(list);
    }
    BinaryProcess binary = new BinaryProcess();
    private ArrayList<Vehicle> deSerialBinary(String path) {
        return binary.deSerialization(path);
    }

    YamlProcess yaml = new YamlProcess();

    private void SerialYAML(List list) {
        yaml.Serialization(list);
    }

    private ArrayList deSerialYAML(String path) {
        return yaml.deSerialization(path);
    }

    XmlProcess xml = new XmlProcess();

    private void SerialXML(List list) {
        xml.Serialization(list);
    }

    private ArrayList deSerialXML(String path) {
        return xml.deSerialization(path);
    }

    //GET INFORMATION FROM FILE
    public void Json() {
        z++;
        loadList(3, getPath());
    }

    public void ZIP() {
        z++;
        loadList(4, getPath());
    }

    public void Cryptography() {
        z++;
        loadList(5, getPath());
    }

    public void XML() {
        z++;
        loadList(1, getPath());
    }

    public void Binary() {
        z++;
        loadList(0, getPath());
    }

    public void YAML() {
        z++;
        loadList(2, getPath());
    }

    //WRITE INFORMATION TO FILE
    public void toXML() {
        if (vehicles.size() != 0) {
            SerialXML(vehicles);
            statefield.setText("Данные xml выгружены на диск");
        } else statefield.setText("отсутствуют записываемые данные");
    }

    public void toYAML() {
        if (vehicles.size() != 0) {
            SerialYAML(vehicles);
            statefield.setText("Данные yaml выгружены на диск");
        } else statefield.setText("отсутствуют записываемые данные");
    }

    public void toBinary() {
        if (vehicles.size() != 0) {
            SerialBinary(vehicles);
            statefield.setText("Данные binary выгружены на диск");
        } else statefield.setText("отсутствуют записываемые данные");
    }

    public void toJson() {
        if (vehicles.size() != 0) {
            SerialJson(vehicles);
            statefield.setText("Данные json выгружены на диск");
        } else statefield.setText("отсутствуют записываемые данные");
    }

    public void toFile() {
        if (vehicles.size() == 0) {
            statefield.setText("Отсутствуют записываемые данные");
            return;
        }
        SerialXML(vehicles);
        SerialBinary(vehicles);
        SerialYAML(vehicles);
        SerialJson(vehicles);
        CrytData();
        CreateZip();
        statefield.setText("Данные выгружены на диск");
    }


    //INTERFACE MAINTEANCE
    public void goData() {
        main.setVisible(false);
        datapane.setVisible(true);
        pathfield.setText("C:\\tmp\\");
    }


    private int z = 0;

    private int currentID = 0;

    public void change() {
        Vehicle v = newVehicle();
        vehicles.set(currentID, v);
    }

    public void addVehicle() {
        Vehicle v = newVehicle();
        vehicles.add(v);
    }

    public Vehicle newVehicle() {
        Vehicle v = currentVehicle;
        int speed = Integer.decode(tspeed.getText());
        int pass = Integer.decode(tpassag.getText());
        String name = tname.getText();
        String firm = tfirm.getText();
        try {

            if (v.getVtype().equals(vehicleType.lightvehicle)) {
                LightVehicle vehicle = new LightVehicle();
                vehicle.setVtype(vehicleType.lightvehicle);
                vehicle.setMtype(v.getMtype());
                vehicle.setName(name);
                vehicle.setFirm(firm);
                vehicle.setPassengers(pass);
                vehicle.setMaxSpeed(speed);
                vehicle.setBtype(bodyType.valueOf(tpar1.getText()));
                boolean a = false;
                if (tpar3.getText().charAt(0) == 'А') {
                    a = true;
                }
                vehicle.setAutoTransmission(a);
                vehicle.setHp(Integer.decode(tpar2.getText()));
                return vehicle;
            } else {
                if (v.getVtype().equals(vehicleType.freightveicle)) {
                    FreightVehicle vehicle = new FreightVehicle();
                    vehicle.setVtype(vehicleType.freightveicle);
                    vehicle.setMtype(v.getMtype());
                    vehicle.setName(name);
                    vehicle.setFirm(firm);
                    vehicle.setPassengers(pass);
                    vehicle.setMaxSpeed(speed);
                    Cargo c = new Cargo();
                    vehicle.setMaxWeight(Integer.decode(tpar1.getText()));
                    c.setCtype(cargoType.valueOf(tpar2.getText()));
                    c.setWheel(Integer.decode(tpar3.getText()));
                    c.setVolume(Integer.decode(tpar4.getText()));
                    vehicle.setCargo(c);
                    return vehicle;
                } else {
                    if (v.getVtype().equals(vehicleType.motocycle)) {
                        Motocycle vehicle = new Motocycle();
                        vehicle.setVtype(vehicleType.motocycle);
                        vehicle.setMtype(v.getMtype());
                        vehicle.setName(name);
                        vehicle.setFirm(firm);
                        vehicle.setPassengers(pass);
                        vehicle.setMaxSpeed(speed);
                        vehicle.setMotorVolume(Integer.decode(tpar1.getText()));
                        return vehicle;
                    } else {
                        if (v.getVtype().equals(vehicleType.scooter)) {
                            Scooter vehicle = new Scooter();
                            vehicle.setVtype(vehicleType.scooter);
                            vehicle.setMtype(MotorType.electric);
                            vehicle.setName(name);
                            vehicle.setFirm(firm);
                            vehicle.setPassengers(pass);
                            vehicle.setMaxSpeed(speed);
                            vehicle.setPowerReserve(Integer.decode(tpar1.getText()));
                            return vehicle;
                        } else {
                            if (v.getVtype().equals(vehicleType.electriccar)) {
                                ElectricCar vehicle = new ElectricCar();
                                vehicle.setVtype(vehicleType.electriccar);
                                vehicle.setMtype(MotorType.electric);
                                vehicle.setName(name);
                                vehicle.setFirm(firm);
                                vehicle.setPassengers(pass);
                                vehicle.setMaxSpeed(speed);
                                vehicle.setBtype(bodyType.valueOf(tpar1.getText()));
                                boolean a = false;
                                if (tpar3.getText().charAt(0) == 'А') {
                                    a = true;
                                }
                                vehicle.setAutoTransmission(a);
                                vehicle.setHp(Integer.decode(tpar2.getText()));
                                vehicle.setPowerReserve(Integer.decode(tpar4.getText()));
                                return vehicle;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void placeClear() {
        param1.setVisible(false);
        tpar1.setVisible(false);
        param2.setVisible(false);
        tpar2.setVisible(false);
        param3.setVisible(false);
        tpar3.setVisible(false);
        param4.setVisible(false);
        tpar4.setVisible(false);
        param5.setVisible(false);
        tpar5.setVisible(false);
        param6.setVisible(false);
        tpar6.setVisible(false);
        ttype.setText("");
        tname.setText("");
        tfirm.setText("");
        tspeed.setText("");
        tpassag.setText("");
        tmotor.setText("");
        currentVehicle = new Vehicle();

    }

    public void paneGoList() {
        if (z != 0)
            MenuActions();
        else {
            return;
        }
        main.setVisible(false);
        list.setVisible(true);
        goList();
    }

    public void delete() {
        int k = table.getSelectionModel().getFocusedIndex();
        vehicles.remove(k);
        goList();
    }

    public void GoMain() {
        list.setVisible(false);
        datapane.setVisible(false);
        main.setVisible(true);
    }

    public void MenuActions() {

        MenuItem b1 = new MenuItem("sedan");
        MenuItem b2 = new MenuItem("suv");
        MenuItem b3 = new MenuItem("wagon");
        MenuItem b4 = new MenuItem("coupe");
        MenuItem b5 = new MenuItem("hatchback");
        MenuItem b6 = new MenuItem("pickup");
        MenuItem b7 = new MenuItem("sport");
        MenuItem b8 = new MenuItem("van");
        MenuItem b9 = new MenuItem("crossover");

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b1.getText());
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b2.getText());
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b3.getText());
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b4.getText());
            }
        });
        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b5.getText());
            }
        });
        b6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b6.getText());
            }
        });
        b7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b7.getText());
            }
        });
        b8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b8.getText());
            }
        });
        b9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.lightvehicle) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tpar1.setText(b9.getText());
            }
        });
        bodymenu.getItems().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);
        MenuItem c1 = new MenuItem("eurotent");
        MenuItem c2 = new MenuItem("refrigerator");
        MenuItem c3 = new MenuItem("roadtrain");
        MenuItem c4 = new MenuItem("van");
        MenuItem c5 = new MenuItem("tanker");
        MenuItem c6 = new MenuItem("platform");
        c1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.freightveicle))
                    tpar2.setText(c1.getText());
            }
        });
        c2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.freightveicle))
                    tpar2.setText(c2.getText());
            }
        });
        c3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.freightveicle))
                    tpar2.setText(c3.getText());
            }
        });
        c4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.freightveicle))
                    tpar2.setText(c4.getText());
            }
        });
        c5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.freightveicle))
                    tpar2.setText(c5.getText());
            }
        });
        c6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.freightveicle))
                    tpar2.setText(c6.getText());
            }
        });
        cargomenu.getItems().addAll(c1, c2, c3, c4, c5, c6);

        MenuItem t1 = new MenuItem("motocycle");
        MenuItem t2 = new MenuItem("scooter");
        MenuItem t3 = new MenuItem("electriccar");
        MenuItem t4 = new MenuItem("lightvehicle");
        MenuItem t5 = new MenuItem("freightveicle");
        t1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentVehicle.setVtype(vehicleType.motocycle);
                currentVehicle.setMtype(MotorType.petrol);
                loadView(-1, currentVehicle);
                //        ttype.setText("motocycle");
            }
        });
        t2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentVehicle.setVtype(vehicleType.scooter);
                currentVehicle.setMtype(MotorType.electric);
                loadView(-1, currentVehicle);
                //          ttype.setText("scooter");
                //            tmotor.setText("electric");
            }
        });
        t3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentVehicle.setVtype(vehicleType.electriccar);
                currentVehicle.setMtype(MotorType.electric);
                loadView(-1, currentVehicle);
                //              ttype.setText("electriccar");
//                tmotor.setText("electric");
            }
        });
        t4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentVehicle.setVtype(vehicleType.lightvehicle);
                currentVehicle.setMtype(MotorType.petrol);
                loadView(-1, currentVehicle);
                //      ttype.setText("lightvehicle");
            }
        });
        t5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ////    ttype.setText("freightveicle");
                currentVehicle.setVtype(vehicleType.freightveicle);
                currentVehicle.setMtype(MotorType.disel);
                loadView(-1, currentVehicle);
            }
        });
        typemenu.getItems().add(t1);
        typemenu.getItems().add(t2);
        typemenu.getItems().add(t3);
        typemenu.getItems().add(t4);
        typemenu.getItems().add(t5);
        MenuItem m1 = new MenuItem("petrol");
        MenuItem m2 = new MenuItem("disel");
        MenuItem m3 = new MenuItem("electric");
        m1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(currentVehicle.getVtype().equals(vehicleType.scooter) || currentVehicle.getVtype().equals(vehicleType.electriccar)))
                    tmotor.setText("petrol");
            }
        });
        m2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(currentVehicle.getVtype().equals(vehicleType.scooter) || currentVehicle.getVtype().equals(vehicleType.electriccar)))
                    tmotor.setText("disel");
            }
        });
        m3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentVehicle.getVtype().equals(vehicleType.scooter) || currentVehicle.getVtype().equals(vehicleType.electriccar))
                    tmotor.setText("electric");
            }
        });
        motormenu.getItems().add(m1);
        motormenu.getItems().add(m2);
        motormenu.getItems().add(m3);


    }

    public void test() {
        List list = new ArrayList<Vehicle>();
        LightVehicle vehicle = new LightVehicle("X6", "BMW", 290, 5, true, bodyType.crossover, 462);
        LightVehicle vehicle1 = new LightVehicle("M1 sport", "BMW", 270, 5, false, bodyType.sport, 550);
        LightVehicle vehicle3 = new LightVehicle("GT-R", "Nissan", 320, 2, true, bodyType.sport, 700);
        LightVehicle vehicle4 = new LightVehicle("MPV", "Mazda", 210, 7, true, bodyType.van, 200);
        FreightVehicle f = new FreightVehicle("same", "BMW", 120, 2, new Cargo(cargoType.platform, 8, 40), 50);
        Motocycle moto = new Motocycle("R1", 560);
        moto.setFirm("Yamaha");
        moto.setMaxSpeed(280);
        Motocycle moto2 = new Motocycle("something", 1000);
        moto2.setFirm("BMW");
        moto2.setMaxSpeed(310);
        Scooter s = new Scooter("cls 1000r", "Xiaomi", 25, 40);
        Scooter ss = new Scooter("govno", "Kolobyke", 15, 70);

        ElectricCar tesl = new ElectricCar("model-s", "Tesla", 290, 4, true, bodyType.sedan, 600, 300);
        tesl.setFirm("Tesla");
        list.add(s);
        list.add(ss);
        list.add(tesl);
        list.add(vehicle);
        list.add(vehicle1);
        list.add(vehicle3);
        list.add(vehicle4);
        list.add(f);
        list.add(moto);
        list.add(vehicle);
        list.add(f);
        list.add(tesl);
        list.add(vehicle4);
        list.add(f);
        list.add(moto);
        list.add(moto2);
        SerialXML(list);
        list.add(s);
        SerialBinary(list);
        SerialYAML(list);
    }

    public void paneGoView() {
        int k = table.getSelectionModel().getFocusedIndex();
        loadView(k, null);
        view.setVisible(true);
        list.setVisible(false);
    }

    private Vehicle currentVehicle = new Vehicle();

    public void loadView(int i, Vehicle tmp) {
        param1.setVisible(false);
        tpar1.setVisible(false);
        param2.setVisible(false);
        tpar2.setVisible(false);
        param3.setVisible(false);
        tpar3.setVisible(false);
        param4.setVisible(false);
        tpar4.setVisible(false);
        param5.setVisible(false);
        tpar5.setVisible(false);
        param6.setVisible(false);
        tpar6.setVisible(false);
        Vehicle vehicle;
        if (i == -1) {
            vehicle = tmp;
        } else {
            currentID = i;
            vehicle = (Vehicle) vehicles.get(i);
            currentVehicle = new Vehicle(vehicle.getName(), vehicle.getFirm(), vehicle.getMaxSpeed(), vehicle.getPassengers());
            currentVehicle.setMtype(vehicle.getMtype());
            currentVehicle.setVtype(vehicle.getVtype());
        }
        if (vehicle.getVtype().equals(vehicleType.lightvehicle)) {

            LightVehicle v = new LightVehicle();
            if (i == -1) {
                v = new LightVehicle(currentVehicle);
            } else {
                v = (LightVehicle) vehicles.get(i);
            }
            param1.setVisible(true);
            tpar1.setVisible(true);
            param2.setVisible(true);
            tpar2.setVisible(true);
            param3.setVisible(true);
            tpar3.setVisible(true);
            param1.setText("Тип корпуса");
            tpar1.setText(v.getBtype() + "");
            param2.setText("Лошадиные силы,HP");
            tpar2.setText(v.getHp() + "");
            param3.setText("Коробка передач");
            if (v.isAutoTransmission()) {
                tpar3.setText("Автомат");
            } else {
                tpar3.setText("Механика");
            }
        } else if (vehicle.getVtype().equals(vehicleType.freightveicle)) {
            FreightVehicle v = new FreightVehicle();
            if (i == -1) {
                v = new FreightVehicle(currentVehicle);
            } else {
                v = (FreightVehicle) vehicles.get(i);
            }
            param1.setVisible(true);
            tpar1.setVisible(true);
            param2.setVisible(true);
            tpar2.setVisible(true);
            param3.setVisible(true);
            tpar3.setVisible(true);
            param4.setVisible(true);
            tpar4.setVisible(true);
            Cargo c = v.getCargo();
            param1.setText("Грузоподьеность авто,Тонн");
            param2.setText("Тип груза");
            param3.setText("Колеса груза,Шт");
            param4.setText("Вес груза,Тонн");
            tpar1.setText(v.getMaxWeight() + "");
            tpar2.setText(c.getCtype() + "");
            tpar3.setText(c.getWheel() + "");
            tpar4.setText(c.getVolume() + "");
        } else if (vehicle.getVtype().equals(vehicleType.motocycle)) {
            Motocycle v = new Motocycle();
            if (i == -1) {
                v = new Motocycle(currentVehicle);
            } else {
                v = (Motocycle) vehicles.get(i);
            }

            param1.setVisible(true);
            tpar1.setVisible(true);
            param1.setText("Обьем двигателя,см3");
            tpar1.setText(v.getMotorVolume() + "");
        } else if (vehicle.getVtype().equals(vehicleType.electriccar)) {
            ElectricCar v = new ElectricCar();
            if (i == -1) {
                v = new ElectricCar(currentVehicle);
            } else {
                v = (ElectricCar) vehicles.get(i);
            }
            param1.setVisible(true);
            tpar1.setVisible(true);
            param2.setVisible(true);
            tpar2.setVisible(true);
            param3.setVisible(true);
            tpar3.setVisible(true);
            param4.setVisible(true);
            tpar4.setVisible(true);
            param1.setText("Тип корпуса");
            tpar1.setText(v.getBtype() + "");
            param2.setText("Лошадиные силы,HP");
            tpar2.setText(v.getHp() + "");
            param3.setText("Коробка передач");
            param4.setText("Дальность хода,Км");
            tpar4.setText(v.getPowerReserve() + "");
            if (v.isAutoTransmission()) {
                tpar3.setText("Автомат");
            } else {
                tpar3.setText("Механика");
            }
        } else if (vehicle.getVtype().equals(vehicleType.scooter)) {
            Scooter v = new Scooter();
            if (i == -1) {
                v = new Scooter(currentVehicle);
            } else {
                v = (Scooter) vehicles.get(i);
            }
            param1.setVisible(true);
            tpar1.setVisible(true);
            param1.setText("Дальность хода,Км");
            tpar1.setText(v.getPowerReserve() + "");
        }


        ttype.setText(vehicle.getVtype() + "");
        tmotor.setText(vehicle.getMtype() + "");
        tname.setText(vehicle.getName());
        tfirm.setText(vehicle.getFirm());
        tspeed.setText(vehicle.getMaxSpeed() + "");
        tpassag.setText(vehicle.getPassengers() + "");
    }

    public void goBack() {
        view.setVisible(false);
        list.setVisible(true);
        goList();
    }

    public void goList() {
        //loadList(0);
        arr.clear();
        for (int i = 0; i < vehicles.size(); i++) {
            String p1 = "";
            String p2 = "";
            String p3 = "";
            String p6 = "";
            int p4 = 0;
            int p5 = 0;
            Vehicle v = (Vehicle) vehicles.get(i);
            if (v.getVtype().equals(vehicleType.lightvehicle)) {
                p1 = "Легковой";
            } else {
                if (v.getVtype().equals(vehicleType.freightveicle)) {
                    p1 = "Грузовой";
                } else {
                    if (v.getVtype().equals(vehicleType.motocycle)) {
                        p1 = "Мотоцикл";
                    } else {
                        if (v.getVtype().equals(vehicleType.scooter)) {
                            p1 = "Самокат";
                        } else {
                            if (v.getVtype().equals(vehicleType.electriccar)) {
                                p1 = "Электрокар";
                            } else {

                            }
                        }
                    }
                }
            }
            if (!v.getName().equals("")) {
                p2 = v.getName();
            }
            if (!v.getFirm().equals("")) {
                p3 = v.getFirm();
            }
            if (v.getMaxSpeed() != 0) {
                p4 = v.getMaxSpeed();
            }
            if (v.getPassengers() != 0) {
                p5 = v.getPassengers();
            }
            if (v.getMtype() != null) {
                if (v.getMtype().equals(MotorType.petrol)) {
                    p6 = "Бензиновый";
                } else {
                    if (v.getMtype().equals(MotorType.disel)) {
                        p6 = "Дизельный";
                    } else {
                        if (v.getMtype().equals(MotorType.electric)) {
                            p6 = "Электрический";
                        }
                    }
                }
            }
            arr.add(new VehicleTable(p1, p2, p3, p4, p5, p6));
        }
        k1.setCellValueFactory(new PropertyValueFactory<VehicleTable, String>("type"));
        k2.setCellValueFactory(new PropertyValueFactory<VehicleTable, String>("name"));
        k3.setCellValueFactory(new PropertyValueFactory<VehicleTable, String>("firm"));
        k6.setCellValueFactory(new PropertyValueFactory<VehicleTable, String>("motor"));
        k4.setCellValueFactory(new PropertyValueFactory<VehicleTable, Integer>("speed"));
        k5.setCellValueFactory(new PropertyValueFactory<VehicleTable, Integer>("pass"));
        table.setItems(arr);
    }


    private ObservableList<VehicleTable> arr = FXCollections.observableArrayList();

    public void exit() {
        System.exit(3);
    }

    @FXML
    private Button bb;
    @FXML
    private TableView<VehicleTable> table;
    @FXML
    private TableColumn<VehicleTable, Integer> k4;
    @FXML
    private TableColumn<VehicleTable, Integer> k5;
    @FXML
    private TableColumn<VehicleTable, String> k1;
    @FXML
    private TableColumn<VehicleTable, String> k2;
    @FXML
    private TableColumn<VehicleTable, String> k3;
    @FXML
    private TableColumn<VehicleTable, String> k6;

    @FXML
    private Pane main;
    @FXML
    private Pane list;
    @FXML
    private Pane view;
    @FXML
    private Label param1;
    @FXML
    private Label param2;
    @FXML
    private Label param3;
    @FXML
    private Label param4;
    @FXML
    private Label param5;
    @FXML
    private Label param6;
    @FXML
    private TextField tpar1;
    @FXML
    private TextField tpar2;
    @FXML
    private TextField tpar3;
    @FXML
    private TextField tpar4;
    @FXML
    private TextField tpar5;
    @FXML
    private TextField tpar6;
    @FXML
    private TextField ttype;
    @FXML
    private TextField tname;
    @FXML
    private TextField tfirm;
    @FXML
    private TextField tspeed;
    @FXML
    private TextField tmotor;
    @FXML
    private TextField tpassag;

    @FXML
    private Pane datapane;
    @FXML
    private ContextMenu typemenu;

    @FXML
    private ContextMenu motormenu;
    @FXML
    private ContextMenu bodymenu;
    @FXML
    private ContextMenu cargomenu;
    @FXML
    private TextField pathfield;
    @FXML
    private TextField statefield;
}
