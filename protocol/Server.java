package protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import backend.src.application.Route;

public class Server implements Runnable {
    private Integer port;
    private String domain;
    private ServerSocket serverSocket;
    private Route route;

    public Server(String domain, Integer port) throws IOException {
        this.domain = domain;
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public void run() {
        try {
            System.out.println("Server is listening on port " + port + " at domain " + domain);
            while (true) {
                Socket socket = this.serverSocket.accept();
                System.out.println("New client connected");
                Channel channel = new Channel(socket, route);
                new Thread(channel).start();
            }
        } catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void listen() {
        new Thread(this).start();
    }
}
