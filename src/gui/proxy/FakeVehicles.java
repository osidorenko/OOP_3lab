package gui.proxy;

import gui.VehicleTable;
import vehicles.Vehicle;
import vehicles.VehicleInterface;
import vehicles.composite.Vehicles;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FakeVehicles implements Proxy {
    private Vehicles vehicles;
    private ArrayList<LinkedHashMap> list;

    public FakeVehicles(Vehicles pvehicles) {
        this.vehicles = pvehicles;
        list = vehicles.getAllHash();

    }

    public void change(int i, VehicleInterface vehicle) {
        Vehicle v = (Vehicle) vehicle;
        LinkedHashMap m = new LinkedHashMap();
        m.put("name", v.getName());
        m.put("firm", v.getFirm());
        m.put("maxSpeed", v.getMaxSpeed());
        m.put("vtype", v.getVtype());
        m.put("passengers", v.getPassengers());
        m.put("mtype", v.getMtype());
        this.list.set(i, m);
    }

    @Override
    public ArrayList getList() {
        ArrayList ret = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap map = list.get(i);
            if (map.size() == 0) {
                continue;
            }
            String type = map.get("vtype").toString();
            String name = map.get("name").toString();
            String firm = map.get("firm").toString();
            int speed = Integer.decode(map.get("maxSpeed").toString());
            int pass = Integer.decode(map.get("passengers").toString());
            String motor = map.get("mtype").toString();
            VehicleTable end = new VehicleTable(type, name, firm, speed, pass, motor);
            ret.add(end);
        }
        return ret;
    }

    @Override
    public void add(VehicleInterface vehicle) {
        Vehicle v = (Vehicle) vehicle;
        LinkedHashMap m = new LinkedHashMap();
        m.put("name", v.getName());
        m.put("firm", v.getFirm());
        m.put("maxSpeed", v.getMaxSpeed());
        m.put("vtype", v.getVtype());
        m.put("passengers", v.getPassengers());
        m.put("mtype", v.getMtype());
        this.list.add(m);
    }

    @Override
    public void remove(int i) {
        this.list.remove(i);
    }
}
