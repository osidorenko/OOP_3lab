package vehicles;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.LinkedHashMap;

//@XmlType(name = "motocycle")
//@XmlRootElement
public class Motocycle extends TwoWheeledVehicle implements Serializable {
    @XmlAttribute
    private int motorVolume=0;
    public Motocycle(String name) {
        super(name);
        super.setMtype(MotorType.petrol);
        super.setPassengers(2);
        super.setVtype(vehicleType.motocycle);
    }

    public Motocycle(Vehicle v) {
        super(v);
    }

    public Motocycle(String name, String firm, int maxSpeed, int passengers, int motorVolume) {
        super(name, firm, maxSpeed, passengers);
        this.motorVolume = motorVolume;
        super.setVtype(vehicleType.motocycle);
    }

    public Motocycle(LinkedHashMap map) {
        super(map);
        //super.setMtype(MotorType.petrol);
        super.setVtype(vehicleType.motocycle);
        this.motorVolume = Integer.decode(map.get("motorVolume").toString());
    }

    @Override
    public void create(LinkedHashMap map) {
        super.create(map);
        super.setVtype(vehicleType.motocycle);
        this.motorVolume = Integer.decode(map.get("motorVolume").toString());
    }

    public Motocycle(String name, int motorVolume) {
        super(name);
        this.motorVolume = motorVolume;
        super.setMtype(MotorType.petrol);
        super.setPassengers(2);
        super.setVtype(vehicleType.motocycle);
    }

    public Motocycle(int motorVolume) {
        this.motorVolume = motorVolume;
        super.setMtype(MotorType.petrol);
        super.setPassengers(2);
        super.setVtype(vehicleType.motocycle);
    }

    public Motocycle(){
        this.setVtype(vehicleType.motocycle);

    }

    public int getMotorVolume() {
        return motorVolume;
    }

    public void setMotorVolume(int motorVolume) {
        this.motorVolume = motorVolume;
    }

    @Override
    public String toString() {
        return "Motocycle{" +
                "motorVolume=" + motorVolume +
                super.toString()+'}';
    }
}
