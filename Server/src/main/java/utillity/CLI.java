package main.java.utillity;

import main.java.server.IObserveServer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CLI implements IObservableCLI, Runnable{
    private final BufferedReader inputReader;
    private final OutputStreamWriter outputWriter;
    private boolean state;
    private List<IObserveServer> observers;

    public CLI(InputStream inputStream, OutputStream outputStream) {
        this.inputReader = new BufferedReader(new InputStreamReader(inputStream));
        this.outputWriter = new OutputStreamWriter(outputStream);
        state = false;
        observers= new ArrayList<>();
    }

    private void setState(boolean state) {
        this.state = state;
        notify(state);
    }

    @Override
    public void add(IObserveServer observer) {
        observers.add(observer);
    }

    @Override
    public void notify(boolean state) {
        for(IObserveServer observer : observers) {
            observer.update(state);
        }
    }

    @Override
    public void run() {
        String command = "";

        try {
            while(!command.equals("shutdown")) {

                outputWriter.write("Enter command: ");
                outputWriter.flush();

                command = inputReader.readLine().toLowerCase();

                if(command.equals("start")) {
                    setState(true);
                }
                else if(command.equals("shutdown")) {
                    setState(false);
                }
                else {
                    outputWriter.write("unknown command.\n");
                }
            }

            setState(false); // the operator shutdown the server
            inputReader.close();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
