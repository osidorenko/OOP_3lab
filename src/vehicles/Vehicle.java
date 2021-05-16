package vehicles;


import javax.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.LinkedHashMap;


//@XmlType(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement
public class Vehicle implements Serializable, VehicleInterface {
    private String name = "";
    private String firm = "";
    private int maxSpeed = 0;
    private MotorType mtype = MotorType.petrol;
    private vehicleType vtype;

    private int passengers;

    public Vehicle() {

    }

    public Vehicle(Vehicle v) {
        name = v.getName();
        firm = v.getFirm();
        maxSpeed = v.getMaxSpeed();
        mtype = v.getMtype();
        vtype = v.getVtype();
        passengers = v.getPassengers();
    }

    public Vehicle(LinkedHashMap map) {
        setPassengers(Integer.decode(map.get("passengers").toString()));
        setFirm(map.get("firm").toString());
        setName(map.get("name").toString());
        setMtype(MotorType.valueOf(map.get("mtype").toString()));
        setMaxSpeed(Integer.decode(map.get("maxSpeed").toString()));
    }


    public vehicleType getVtype() {
        return vtype;
    }

    @Override
    public boolean isInit() {
        if(!name.equals("")){
            if(!firm.equals("")){
                if(!mtype.equals(null)){
                    if(maxSpeed!=0){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setVtype(vehicleType vtype) {
        this.vtype = vtype;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public Vehicle(String name) {
        this.name = name;
    }


    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", firm='" + firm + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", mtype=" + mtype +
                ", vtype=" + vtype +
                ", passengers=" + passengers +
                '}';
    }

    public Vehicle(String name, int maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public Vehicle(String name, String firm, int maxSpeed, int passengers) {
        //this.mtype = mtype;
        this.name = name;
        this.firm = firm;
        this.maxSpeed = maxSpeed;
        this.passengers = passengers;
    }

    public MotorType getMtype() {
        return mtype;
    }

    //@XmlElement
    public void setMtype(MotorType mtype) {
        this.mtype = mtype;
    }

    public String getName() {
        return name;
    }

    //@XmlElement
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void create(LinkedHashMap map) {
        setPassengers(Integer.decode(map.get("passengers").toString()));
        setFirm(map.get("firm").toString());
        setName(map.get("name").toString());
        setMtype(MotorType.valueOf(map.get("mtype").toString()));
        setMaxSpeed(Integer.decode(map.get("maxSpeed").toString()));
    }
}

