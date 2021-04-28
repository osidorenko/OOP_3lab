package sample;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

//@XmlRootElement
public class TwoWheeledVehicle extends Vehicle implements Serializable {

    private double weight;

    public TwoWheeledVehicle(String name) {
        super(name);
    }

    public TwoWheeledVehicle() {

    }


}
