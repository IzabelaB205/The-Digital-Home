package main.java.utillity;

import main.java.server.Server;

public class Driver {

    private final static int PORT = 2424;

    public static void main(String[] args) {
        CLI cli = new CLI(System.in, System.out);
        Server server = new Server(PORT);
        cli.add(server);
        new Thread(cli).start();
    }
}
