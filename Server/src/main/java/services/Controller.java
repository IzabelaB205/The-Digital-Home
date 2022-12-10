package main.java.services;

import main.java.memory.CacheUnit;

public class Controller<T> implements IController<T>{
    private final CacheUnit<T> cacheUnit;

    public Controller() {
        cacheUnit = new CacheUnit<>();
    }

    @Override
    public T[] listDevices() {
        return cacheUnit.listDevices();
    }

    @Override
    public void switchDevice(String body) {
        cacheUnit.switchDevice(body);
    }

    @Override
    public void setValueDevice(String body) {
        cacheUnit.setValueDevice(body);
    }
}
