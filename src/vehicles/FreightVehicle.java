package vehicles;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedHashMap;


//@XmlType(name = "FreightVehicle")
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
//@XmlSeeAlso(FreightVehicle.Cargo.class)
public class FreightVehicle extends Vehicle implements Serializable {
    //  @XmlElement
    //@JsonIgnore
    private Cargo cargo = new Cargo(cargoType.platform,0,0);
    @XmlAttribute
    private int maxWeight = 0;

    public FreightVehicle() {

    }

    public FreightVehicle(LinkedHashMap map) {
        super(map);
        super.setVtype(vehicleType.freightveicle);
        this.maxWeight = Integer.decode(map.get("maxWeight").toString());
        LinkedHashMap m = (LinkedHashMap) map.get("cargo");
        cargoType t = cargoType.valueOf(m.get("ctype").toString());
        int wh = Integer.decode(m.get("wheel").toString());
        int vol = Integer.decode(m.get("volume").toString());
        this.setCargo(new Cargo(t, wh, vol));
    }

    public FreightVehicle(Vehicle v) {
        super(v);
    }


    public FreightVehicle(LinkedHashMap map, Cargo cargo) {
        super(map);
        this.cargo = cargo;
    }

    @Override
    public void create(LinkedHashMap map) {
        super.create(map);
        super.setVtype(vehicleType.freightveicle);
        this.maxWeight = Integer.decode(map.get("maxWeight").toString());
        LinkedHashMap m = (LinkedHashMap) map.get("cargo");
        cargoType t = cargoType.valueOf(m.get("ctype").toString());
        int wh = Integer.decode(m.get("wheel").toString());
        int vol = Integer.decode(m.get("volume").toString());
        this.setCargo(new Cargo(t, wh, vol));
    }

    public FreightVehicle(String name, String firm, int maxSpeed, int passengers, Cargo cargo, int maxWeight) {
        super(name, firm, maxSpeed, passengers);
        super.setMtype(MotorType.disel);
        super.setVtype(vehicleType.freightveicle);
        this.cargo = cargo;
        this.maxWeight = maxWeight;
    }

    //  @JsonIgnore


    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "FreightVehicle{" +
                "cargo=" + cargo +
                ", maxWeight=" + maxWeight +
                " " + super.toString() + '}';
    }

    @Override
    public vehicleType getVtype() {
        return super.getVtype();
    }

    @Override
    public void setVtype(vehicleType vtype) {
        super.setVtype(vtype);
    }

    @Override
    public int getMaxSpeed() {
        return super.getMaxSpeed();
    }

    @Override
    public void setMaxSpeed(int maxSpeed) {
        super.setMaxSpeed(maxSpeed);
    }

    @Override
    public int getPassengers() {
        return super.getPassengers();
    }

    @Override
    public void setPassengers(int passengers) {
        super.setPassengers(passengers);
    }

    public FreightVehicle(String name) {
        super(name);
    }

    @Override
    public String getFirm() {
        return super.getFirm();
    }

    @Override
    public void setFirm(String firm) {
        super.setFirm(firm);
    }

    public FreightVehicle(String name, int maxSpeed) {
        super(name, maxSpeed);
    }

    public FreightVehicle(String name, String firm, int maxSpeed, int passengers) {
        super(name, firm, maxSpeed, passengers);
    }

    @Override
    public MotorType getMtype() {
        return super.getMtype();
    }

    @Override
    public void setMtype(MotorType mtype) {
        super.setMtype(mtype);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
    //@JsonIgnore

}
