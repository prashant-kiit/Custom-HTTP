package LoadBalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Service implements Runnable {
    private String domain;
    private Integer port;

    public Service(String domain, Integer port) {
        this.domain = domain;
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                // new connection
                try (Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    System.out.println("New client connected to " + domain + ":" + port);

                    // receive the message
                    String message = in.readLine();

                    // send the message
                    out.println("Message received: " + message + " from " + domain + ":" + port);

                } catch (IOException e) {
                    System.out.println("Error in server " + domain + ":" + port + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
