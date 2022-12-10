package main.java.services;

import main.java.memory.CacheUnit;
import main.java.server.Response;

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
    public Response<String> switchDevice(String body) {
        return cacheUnit.switchDevice(body);
    }

    @Override
    public Response<String> setValueDevice(String body) {
        return cacheUnit.setValueDevice(body);
    }
}
