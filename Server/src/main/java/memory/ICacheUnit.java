package main.java.memory;

public interface ICacheUnit<T> {
    T[] listDevices();
    void switchDevice(String body);
    void setValueDevice(String body);
}
