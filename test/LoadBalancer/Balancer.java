package test.LoadBalancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Balancer implements Runnable {
    private String domain;
    private Integer port;

    public Balancer(String domain, Integer port) {
        this.domain = domain;
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Balancer is listening on " + domain + ":" + port);
            while (true) {
                // new connection
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected to Balancer");

                    // queue the socket in macrotaskqueue
                    MacroTaskQueue.getInstance().addSocket(socket);
                } catch (IOException e) {
                    System.out.println("Error in balancer communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
