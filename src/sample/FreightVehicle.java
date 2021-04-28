package sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;


//@XmlType(name = "FreightVehicle")
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
//@XmlSeeAlso(FreightVehicle.Cargo.class)
public class FreightVehicle extends Vehicle implements Serializable {
    //  @XmlElement
    //@JsonIgnore
    private Cargo cargo;
    @XmlAttribute
    private int maxWeight;

    public FreightVehicle() {

    }

    public FreightVehicle(String name, String firm, int maxSpeed, int passengers, Cargo cargo, int maxWeight) {
        super(name, firm, maxSpeed, passengers);
        super.setMtype(MotorType.disel);
        super.setVtype(vehicleType.freightveicle);
        this.cargo = cargo;
        this.maxWeight = maxWeight;
    }

    //  @JsonIgnore


    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "FreightVehicle{" +
                "cargo=" + cargo +
                ", maxWeight=" + maxWeight +
                super.toString() + '}';
    }

    @Override
    public vehicleType getVtype() {
        return super.getVtype();
    }

    @Override
    public void setVtype(vehicleType vtype) {
        super.setVtype(vtype);
    }

    @Override
    public int getMaxSpeed() {
        return super.getMaxSpeed();
    }

    @Override
    public void setMaxSpeed(int maxSpeed) {
        super.setMaxSpeed(maxSpeed);
    }

    @Override
    public int getPassengers() {
        return super.getPassengers();
    }

    @Override
    public void setPassengers(int passengers) {
        super.setPassengers(passengers);
    }

    public FreightVehicle(String name) {
        super(name);
    }

    @Override
    public String getFirm() {
        return super.getFirm();
    }

    @Override
    public void setFirm(String firm) {
        super.setFirm(firm);
    }

    public FreightVehicle(String name, int maxSpeed) {
        super(name, maxSpeed);
    }

    public FreightVehicle(String name, String firm, int maxSpeed, int passengers) {
        super(name, firm, maxSpeed, passengers);
    }

    @Override
    public MotorType getMtype() {
        return super.getMtype();
    }

    @Override
    public void setMtype(MotorType mtype) {
        super.setMtype(mtype);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
    //@JsonIgnore

}
