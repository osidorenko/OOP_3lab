package gui.proxy;

import vehicles.VehicleInterface;

import java.util.ArrayList;

public interface Proxy {
    public ArrayList getList();
    public void add(VehicleInterface vehicle);
    public void remove(int i);
}
