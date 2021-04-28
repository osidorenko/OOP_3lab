package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.omg.PortableServer.ServantRetentionPolicy;

import java.util.HashMap;
import java.util.Map;




//@XmlType(name = "LightVehicle")
//@XmlRootElement

public class LightVehicle extends Vehicle implements Serializable {

    private boolean isAutoTransmission;

    private bodyType btype;
    @XmlAttribute
    private int hp;

    @Override
    public String getFirm() {
        return super.getFirm();
    }

    @Override
    public void setFirm(String firm) {
        super.setFirm(firm);
    }

    public LightVehicle(String name) {
        super(name);
        super.setPassengers(5);
        super.setVtype(vehicleType.lightvehicle);
    }


    public LightVehicle(String name, int maxSpeed, boolean isAutoTransmission, int hp, bodyType pbtype) {
        super(name, maxSpeed);
        super.setPassengers(5);
        this.isAutoTransmission = isAutoTransmission;
        this.hp = hp;
        super.setMtype(MotorType.petrol);
        super.setVtype(vehicleType.lightvehicle);
        this.btype = pbtype;
    }

    @Override
    public String toString() {
        return "LightVehicle{" +
                "isAutoTransmission=" + isAutoTransmission +
                ", btype=" + btype +
                ", hp=" + hp +" "+super.toString()+
                '}';
    }

    @Override
    public void setMtype(MotorType mtype) {
        if (mtype.equals(MotorType.electric)) {
            return;
        }
        super.setMtype(mtype);
    }

    public LightVehicle(){

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
    public MotorType getMtype() {
        return super.getMtype();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public bodyType getBtype() {
        return btype;
    }

    @Override
    public int getPassengers() {
        return super.getPassengers();
    }

    @Override
    public void setPassengers(int passengers) {
        super.setPassengers(passengers);
    }

    public void setBtype(bodyType btype) {
        this.btype = btype;
    }

    public LightVehicle(String name, int maxSpeed) {
        super(name, maxSpeed);
        super.setPassengers(5);
    }

    public boolean isAutoTransmission() {
        return isAutoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        isAutoTransmission = autoTransmission;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}
