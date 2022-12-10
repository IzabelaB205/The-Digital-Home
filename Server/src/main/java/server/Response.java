package main.java.server;

public class Response<T> {
    private T body;

    public void setBody(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }
}
