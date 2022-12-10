package main.java.memory;

import main.java.dm.Device;
import main.java.server.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheUnit<T> implements ICacheUnit<T>{
    private Map<Integer, T> devices;

    public CacheUnit() {
        devices = new HashMap<>();
        initDevicesState();
    }

    private void initDevicesState() {
        devices.put(1, (T) new Device("lamp", 1, true, null));
        devices.put(2, (T) new Device("airconditioner", 2, true, 20));
    }

    @Override
    public T[] listDevices() {
        List<T> deviceList = new ArrayList<>(devices.values());

        return (T[]) deviceList.toArray();
    }

    @Override
    public Response<String> switchDevice(String body) {
        String[] bodySplit = body.split(" ");
        int id = Integer.parseInt(bodySplit[0]);
        String state = bodySplit[1].toLowerCase();
        String responseMessage = "";

        Device device = (Device) devices.get(id);

        if(device != null) {
            if(state.equals("on")) {
                device.setState(true);
                responseMessage = "Device status changed to 'on' successfully";
            }
            else if(state.equals("off")) {
                device.setState(false);
                responseMessage = "Device status changed to 'off' successfully";
            }
            else {
                responseMessage = "Invalid device state - state can be 'on' or 'off'";
            }

            devices.put(id, (T) device); //update device in devices map
        }
        else {
            responseMessage = "Invalid device id - please select an id from the devices list.";
        }

        Response<String> response = new Response<>();
        response.setBody(responseMessage);
        return response;
    }

    @Override
    public Response<String> setValueDevice(String body) {
        String[] bodySplit = body.split(" ");
        int id = Integer.parseInt(bodySplit[0]);
        Integer value = Integer.parseInt(bodySplit[1]);
        String responseMessage = null;

        Device device = (Device) devices.get(id);

        if(device != null) {
            if(value != null) { //if value equals to null - the value is not a number
                device.setValue(value);
                responseMessage = "Device value changed successfully";
            }
            else {
                responseMessage = "Invalid device value - please enter an numeric value";
            }

            devices.put(id, (T) device);
        }
        else {
            responseMessage = "Invalid device id - please select an id from devices list.";
        }

        Response<String> response = new Response<>();
        response.setBody(responseMessage);
        return response;
    }
}
