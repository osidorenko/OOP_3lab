package sample;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Cargo implements Serializable {
    private cargoType ctype;
    private int wheel;
    private int volume;

    public Cargo() {

    }

    public Cargo(cargoType ctype, int wheel, int volume) {
        this.ctype = ctype;
        this.wheel = wheel;
        this.volume = volume;
    }

    public cargoType getCtype() {
        return ctype;
    }

    @XmlElement
    public void setCtype(cargoType ctype) {
        this.ctype = ctype;
    }

    public int getWheel() {
        return wheel;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "ctype=" + ctype +
                ", wheel=" + wheel +
                ", volume=" + volume +
                '}';
    }

    @XmlElement
    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    public int getVolume() {
        return volume;
    }

    @XmlElement
    public void setVolume(int volume) {
        this.volume = volume;
    }
}

