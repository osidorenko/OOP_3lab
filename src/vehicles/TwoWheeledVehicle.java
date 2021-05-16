package vehicles;

import java.io.Serializable;
import java.util.LinkedHashMap;

//@XmlRootElement
public class TwoWheeledVehicle extends Vehicle implements Serializable {

    public TwoWheeledVehicle(String name) {
        super(name);
    }

    public TwoWheeledVehicle() {

    }

    @Override
    public void create(LinkedHashMap map) {
        super.create(map);
    }

    public TwoWheeledVehicle(Vehicle v) {
        super(v);
    }

    public TwoWheeledVehicle(String name, String firm, int maxSpeed, int passengers) {
        super(name, firm, maxSpeed, passengers);
    }

    public TwoWheeledVehicle(LinkedHashMap map) {
        super(map);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
