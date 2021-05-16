package vehicles;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.LinkedHashMap;

//@XmlType(name = "scooter")
@XmlRootElement
public class Scooter extends TwoWheeledVehicle implements Serializable {
    private int powerReserve=0;

    public Scooter() {

    }

    public Scooter(Vehicle v) {
        super(v);

    }

    public Scooter(String name, String firm, int maxSpeed, int powerReserve) {
        super(name, firm, maxSpeed, 1);
        this.powerReserve = powerReserve;
        super.setMtype(MotorType.electric);
        super.setVtype(vehicleType.scooter);
    }

    public Scooter(LinkedHashMap map) {
        super(map);
        super.setMtype(MotorType.electric);
        super.setVtype(vehicleType.scooter);
        this.powerReserve = Integer.decode(map.get("powerReserve").toString());
    }

    @Override
    public void create(LinkedHashMap map) {
        super.create(map);
        super.setMtype(MotorType.electric);
        super.setVtype(vehicleType.scooter);
        this.powerReserve = Integer.decode(map.get("powerReserve").toString());
    }

    public int getPowerReserve() {
        return powerReserve;
    }

    public void setPowerReserve(int powerReserve) {
        this.powerReserve = powerReserve;
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "powerReserve=" + powerReserve +
                super.toString()+'}';
    }
}
