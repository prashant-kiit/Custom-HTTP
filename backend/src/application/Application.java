package backend.src.application;

import protocol.Server;

public class Application {
    public static void main(String[] args) {
        Server server = new Server();
        new Thread(server).start();
    }
}
