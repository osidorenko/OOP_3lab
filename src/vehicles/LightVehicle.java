package vehicles;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

import java.util.LinkedHashMap;


//@XmlType(name = "LightVehicle")
//@XmlRootElement

public class LightVehicle extends Vehicle implements Serializable{

    private boolean isAutoTransmission = false;

    private bodyType btype = bodyType.sedan;
    @XmlAttribute
    private int hp=0;

    @Override
    public String getFirm() {
        return super.getFirm();
    }

    @Override
    public void setFirm(String firm) {
        super.setFirm(firm);
    }

    public LightVehicle(String name) {
        super(name);
        super.setPassengers(5);
        super.setVtype(vehicleType.lightvehicle);
    }

    public LightVehicle(Vehicle v) {
        super(v);

    }

    public LightVehicle(LinkedHashMap map) {
        super(map);
        super.setVtype(vehicleType.lightvehicle);
        this.btype = bodyType.valueOf(map.get("btype").toString());
        this.hp = Integer.decode(map.get("hp").toString());
        this.isAutoTransmission = Boolean.valueOf(map.get("autoTransmission").toString());

    }

    @Override
    public void create(LinkedHashMap map) {
        super.create(map);
        super.setVtype(vehicleType.lightvehicle);
        this.btype = bodyType.valueOf(map.get("btype").toString());
        this.hp = Integer.decode(map.get("hp").toString());
        this.isAutoTransmission = Boolean.valueOf(map.get("autoTransmission").toString());

    }

    public LightVehicle(String name, String firm, int maxSpeed, int pas, boolean isAutoTransmission, bodyType btype, int hp) {
        super(name, firm, maxSpeed, pas);
        super.setVtype(vehicleType.lightvehicle);
        this.isAutoTransmission = isAutoTransmission;
        this.btype = btype;
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "LightVehicle{" +
                "isAutoTransmission=" + isAutoTransmission +
                ", btype=" + btype +
                ", hp=" + hp + " " + super.toString() +
                '}';
    }

    @Override
    public void setMtype(MotorType mtype) {


        super.setMtype(mtype);
    }


    public LightVehicle() {

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

    public bodyType getBtype() {
        return btype;
    }

    @Override
    public int getPassengers() {
        return super.getPassengers();
    }

    @Override
    public void setPassengers(int passengers) {
        super.setPassengers(passengers);
    }

    public void setBtype(bodyType btype) {
        this.btype = btype;
    }

    public LightVehicle(String name, int maxSpeed) {
        super(name, maxSpeed);
        super.setPassengers(5);
    }

    public boolean isAutoTransmission() {
        return isAutoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        isAutoTransmission = autoTransmission;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}
