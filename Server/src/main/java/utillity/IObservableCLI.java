package main.java.utillity;

import main.java.server.IObserveServer;

public interface IObservableCLI {
    void add(IObserveServer observer);
    void notify(boolean state);
}
