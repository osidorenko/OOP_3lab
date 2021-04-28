package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;

//@XmlType(name = "scooter")
@XmlRootElement
public class Scooter extends TwoWheeledVehicle implements Serializable {
    private int powerReserve;

    public Scooter() {

    }

    public Scooter(String name, int pr) {
        super(name);
        super.setMtype(MotorType.electric);
        super.setPassengers(1);
        this.powerReserve = pr;
    }



    public int getPowerReserve() {
        return powerReserve;
    }

    public void setPowerReserve(int powerReserve) {
        this.powerReserve = powerReserve;
    }



}
