package vehicles.composite;

import vehicles.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Vehicles implements VehicleInterface {
    private ArrayList<VehicleInterface> components = new ArrayList<>();

    private ArrayList<LinkedHashMap> list;


    public void clear() {
        this.components.clear();
        this.list.clear();
    }

    public Vehicles(ArrayList<LinkedHashMap> list) {
        this.list = list;
        this.setTypes();
    }

    public Vehicles() {
        this.list = new ArrayList<>();

    }


    private void setTypes() {
        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap map = list.get(i);
            try {
                vehicleType t = vehicleType.valueOf(map.get("vtype").toString());
                if (t.equals(vehicleType.lightvehicle)) {
                    components.add(new LightVehicle());
                } else {
                    if (t.equals(vehicleType.freightveicle)) {
                        components.add(new FreightVehicle());
                    } else {
                        if (t.equals(vehicleType.electriccar)) {
                            components.add(new ElectricCar());
                        } else {
                            if (t.equals(vehicleType.motocycle)) {
                                components.add(new Motocycle());
                            } else {
                                if (t.equals(vehicleType.scooter)) {
                                    components.add(new Scooter());
                                } else {
                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {

            }
        }

    }

    public void construct() {
        ArrayList<Vehicle> ret = new ArrayList<>();
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getVtype() != null) {
                if (!components.get(i).isInit())
                    components.get(i).create(list.get(i));
            } else components.get(i).create(null);
        }
    }

    public ArrayList<Vehicle> getList() {
        ArrayList<Vehicle> ret = new ArrayList<>();
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).isInit()) {
                ret.add((Vehicle) components.get(i));
            } else {
                Vehicles tmp = (Vehicles) components.get(i);
                //(Vehicles)components.get(i);
                ret.addAll(tmp.getList());
            }
        }
        return ret;
    }

    public ArrayList<LinkedHashMap> getAllHash() {
        if (components.size() == 0) {
            return null;

        }
        ArrayList<LinkedHashMap> maps = new ArrayList<>();
        int i = 0;
        if (list.size() != 0) {
            int l = list.size();
            while (i < l) {
                maps.add(list.get(i));
                i++;
            }
        }
        for (int j = i; j < components.size(); j++) {
            if (components.get(j).isInit()) {
                LinkedHashMap m = new LinkedHashMap();
                Vehicle v = (Vehicle) components.get(j);
                m.put("name", v.getName());
                m.put("firm", v.getFirm());
                m.put("maxSpeed", v.getMaxSpeed());
                m.put("vtype", v.getVtype());
                m.put("passengers", v.getPassengers());
                m.put("mtype", v.getMtype());
                maps.add(m);

            } else {
                Vehicles tmp = (Vehicles) components.get(j);
                maps.addAll(tmp.getAllHash());
            }
        }


        return maps;
    }

    public void add(VehicleInterface component) {

        components.add(component);
    }

    public void remove(VehicleInterface component) {
        components.remove(component);
    }


    @Override
    public void create(LinkedHashMap map) {
        this.construct();
    }

    @Override
    public vehicleType getVtype() {
        return null;
    }

    @Override
    public boolean isInit() {
        return false;
    }
}
