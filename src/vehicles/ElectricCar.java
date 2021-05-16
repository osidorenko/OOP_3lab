package vehicles;

import java.io.Serializable;
import java.util.LinkedHashMap;

//@XmlType(name = "electriccar")
//@XmlRootElement

public class ElectricCar extends LightVehicle  implements Serializable {
    private int powerReserve=0;

    public ElectricCar(String name) {
        super(name);
        super.setMtype(MotorType.electric);
    }

    public ElectricCar(Vehicle v) {
        super(v);
    }

    @Override
    public void create(LinkedHashMap map) {
        super.create(map);
        super.setVtype(vehicleType.electriccar);
        super.setMtype(MotorType.electric);
        this.powerReserve = Integer.decode(map.get("powerReserve").toString());

    }

    @Override
    public void setMtype(MotorType mtype) {
        super.setMtype(mtype);
    }

    @Override
    public int getMaxSpeed() {
        return super.getMaxSpeed();
    }

    @Override
    public void setMaxSpeed(int maxSpeed) {
        super.setMaxSpeed(maxSpeed);
    }

    public ElectricCar() {
        super.setVtype(vehicleType.electriccar);
        //super();
    }

    @Override
    public MotorType getMtype() {
        return super.getMtype();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public bodyType getBtype() {
        return super.getBtype();
    }

    @Override
    public int getPassengers() {
        return super.getPassengers();
    }

    @Override
    public void setPassengers(int passengers) {
        super.setPassengers(passengers);
    }

    @Override
    public void setBtype(bodyType btype) {
        super.setBtype(btype);
    }

    public ElectricCar(String name, int maxSpeed) {
        super(name, maxSpeed);
    }

    @Override
    public boolean isAutoTransmission() {
        return super.isAutoTransmission();
    }

    @Override
    public void setAutoTransmission(boolean autoTransmission) {
        super.setAutoTransmission(autoTransmission);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public void setHp(int hp) {
        super.setHp(hp);
    }



    public ElectricCar(String name, String firm, int maxSpeed, int passengers, boolean isAutoTransmission, bodyType btype, int hp, int powerReserve) {
        super(name, firm, maxSpeed, passengers, isAutoTransmission, btype, hp);
        super.setVtype(vehicleType.electriccar);
        super.setMtype(MotorType.electric);
        this.powerReserve = powerReserve;
    }

    public int getPowerReserve() {
        return powerReserve;
    }

    public void setPowerReserve(int powerReserve) {
        this.powerReserve = powerReserve;
    }

    public ElectricCar(LinkedHashMap map) {
        super(map);
        super.setVtype(vehicleType.electriccar);
        super.setMtype(MotorType.electric);

        //this.setVtype(vehicleType.electriccar);
        //this.setMtype(MotorType.electric);
        this.powerReserve = Integer.decode(map.get("powerReserve").toString());
    }



    @Override
    public String toString() {
        return "ElectricCar{" +
                "powerReserve=" + powerReserve +
                super.toString()+'}';
    }
}
