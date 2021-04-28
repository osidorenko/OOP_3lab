package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

//@XmlType(name = "electriccar")
//@XmlRootElement

public class ElectricCar extends LightVehicle  implements Serializable {
    private int powerReserve;

    public ElectricCar(String name) {
        super(name);
        super.setMtype(MotorType.electric);
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
        super();
    }

    @Override
    public String toString() {
        return super.toString();
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

    public ElectricCar(String name, int maxSpeed, boolean isAutoTransmission, int hp, bodyType bt) {
        super(name, maxSpeed, isAutoTransmission, hp,bt);
        super.setMtype(MotorType.electric);
    }

    public int getPowerReserve() {
        return powerReserve;
    }

    public void setPowerReserve(int powerReserve) {
        this.powerReserve = powerReserve;
    }


}
