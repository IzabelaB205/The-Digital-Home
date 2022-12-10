package main.java.services;

public interface IController<T> {
    T[] listDevices();
    void switchDevice(String body);
    void setValueDevice(String body);
}