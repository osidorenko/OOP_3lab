package vehicles;

import processes.Process;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Vehicles implements VehicleInterface {
    private ArrayList<VehicleInterface> components = new ArrayList<>();
    private ArrayList<Vehicle> vehicles;

    public Vehicles(ArrayList<LinkedHashMap> list) {

    }

    public void add(VehicleInterface component) {
        components.add(component);
    }

    public void remove(VehicleInterface component) {
        components.remove(component);
    }

    @Override
    public void create(LinkedHashMap map) {

        try {
            vehicleType t = vehicleType.valueOf(map.get("vtype").toString());
            if (t.equals(vehicleType.lightvehicle)) {
                vehicles.add(new LightVehicle(map));
            } else {
                if (t.equals(vehicleType.freightveicle)) {
                    vehicles.add(new FreightVehicle(map));
                } else {
                    if (t.equals(vehicleType.electriccar)) {
                        vehicles.add(new ElectricCar(map));
                    } else {
                        if (t.equals(vehicleType.motocycle)) {
                            vehicles.add(new Motocycle(map));
                        } else {
                            if (t.equals(vehicleType.scooter)) {
                                vehicles.add(new Scooter(map));
                            } else {
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {

        }
    }

    @Override
    public vehicleType getVtype() {
        return null;
    }
}
