package main.java.services;

import main.java.server.Response;

public interface IController<T> {
    T[] listDevices();
    Response<String> switchDevice(String body);
    Response<String> setValueDevice(String body);
}