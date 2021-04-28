package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
//@XmlType(name = "motocycle")
//@XmlRootElement
public class Motocycle extends TwoWheeledVehicle implements Serializable {
    @XmlAttribute
    private int motorVolume;
    public Motocycle(String name) {
        super(name);
        super.setMtype(MotorType.petrol);
        super.setPassengers(2);
    }

    public Motocycle(String name, int motorVolume) {
        super(name);
        this.motorVolume = motorVolume;
        super.setMtype(MotorType.petrol);
        super.setPassengers(2);
    }

    public Motocycle(int motorVolume) {
        this.motorVolume = motorVolume;
        super.setMtype(MotorType.petrol);
        super.setPassengers(2);
    }

    public Motocycle(){

    }

    public int getMotorVolume() {
        return motorVolume;
    }

    public void setMotorVolume(int motorVolume) {
        this.motorVolume = motorVolume;
    }


}
