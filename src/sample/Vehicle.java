package sample;


import javax.xml.bind.annotation.*;

import java.io.Serializable;


//@XmlType(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement
public class Vehicle implements Serializable {
    private String name;
    private String firm;
    private int maxSpeed;
    private MotorType mtype;
    private vehicleType vtype;

    private int passengers;
    public Vehicle() {

    }


    public vehicleType getVtype() {
        return vtype;
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



}

