package gui.proxy;

import vehicles.VehicleInterface;
import vehicles.composite.Vehicles;

import java.util.ArrayList;

public class RealVehicles implements Proxy {
    Vehicles vehicles;
    ArrayList list;

    public RealVehicles(Vehicles pvehicles) {
        this.vehicles = pvehicles;
        vehicles.construct();
        list = vehicles.getList();
    }

    public int size(){
        return list.size();
    }

    @Override
    public ArrayList getList() {
        return list;
    }

    @Override
    public void add(VehicleInterface vehicle) {
        list.add(vehicle);
    }

    @Override
    public void remove(int i) {
        list.remove(i);
    }

    public void change(int i, VehicleInterface vehicle) {
        list.set(i, vehicle);

    }
}
