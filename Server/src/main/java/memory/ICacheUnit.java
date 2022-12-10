package main.java.memory;

import main.java.server.Response;

public interface ICacheUnit<T> {
    T[] listDevices();
    Response<String> switchDevice(String body);
    Response<String> setValueDevice(String body);
}
