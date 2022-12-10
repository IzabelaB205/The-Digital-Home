package main.java.server;

public class Request<T> {
    private String command;
    private T body;

    public Request(String command, T body) {
        this.command = command;
        this.body = body;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
