package main.java.server;

import main.java.services.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleRequest<T> implements Runnable {
    private Socket socket;
    private Controller<T> controller;

    public HandleRequest(Socket socket, Controller<T> controller) {
        this.socket = socket;
        this.controller = controller;
    }

    @Override
    public void run() {

        try(PrintWriter output = new PrintWriter(socket.getOutputStream(), true);) {

            output.println("welcome to digital-home");
            Request<String> request;

            do {
                request = getSocketRequest();
                Response<String> response;

                switch (request.getCommand()) {
                    case "listDevices":
                        T[] devices = controller.listDevices();

                        for(T device: devices) {
                            output.println(device);
                        }

                        break;
                    case "switch":
                        response = controller.switchDevice(request.getBody());
                        output.println(response.getBody());
                        break;
                    case "setValue":
                        response = controller.setValueDevice(request.getBody());
                        output.println(response.getBody());
                        break;
                }
            } while (!request.getCommand().equalsIgnoreCase("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request getSocketRequest() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        String[] splitLine = line.split(" ", 2);
        Request<String> request;

        if(splitLine.length == 1) { //listDevices command
            request = new Request<>(splitLine[0], null);
        }
        else { // switch or setValue command
            request = new Request<>(splitLine[0], splitLine[1]);
        }

        return request;
    }
}
