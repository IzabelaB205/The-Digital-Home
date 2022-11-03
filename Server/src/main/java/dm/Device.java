package main.java.dm;

public class Device {
    private String name;
    private int id;
    private boolean state;
    private double value;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public boolean getState() { return state; }

    public void setState(boolean state) { this.state = state; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }
}
