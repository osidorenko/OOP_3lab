package gui;

public class VehicleTable {
    private String type;
    private String name;
    private String firm;
    private int speed;
    private int pass;
    private String motor;

    public VehicleTable(String type, String name, String firm, int speed, int pass, String motor) {
        this.type = type;
        this.name = name;
        this.firm = firm;
        this.speed = speed;
        this.pass = pass;
        this.motor = motor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }
}
