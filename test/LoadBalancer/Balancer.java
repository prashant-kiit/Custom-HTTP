package test.LoadBalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class Balancer implements Runnable {
    private String domain;
    private Integer port;
    private ArrayList<Config> services;
    private Integer semaphore;
    private Integer denominator;

    public Balancer(String domain, Integer port, ArrayList<Config> services) {
        this.domain = domain;
        this.port = port;
        this.services = services;
        this.semaphore = 0;
        this.denominator = services.size();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Balancer is listening on port " + port);
            while (true) {
                // new connection
                try (Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    System.out.println("New client connected to Balancer");

                    // receive the message
                    String message = in.readLine();

                    // load balancing starts here
                    String response = "uninitialized variable";
                    String subdomain = services.get(semaphore).getDomain();
                    Integer subport = services.get(semaphore).getPort();
                    System.out.println("New client directed to " + subdomain + ":" + subport);

                    try (Socket subsocket = new Socket(subdomain, subport);
                            PrintWriter subout = new PrintWriter(subsocket.getOutputStream(), true);
                            BufferedReader subin = new BufferedReader(
                                    new InputStreamReader(subsocket.getInputStream()))) {

                        subout.println(message);
                        response = subin.readLine();
                    } catch (IOException e) {
                        System.out.println("Error in Load Balancing: " + e.getMessage());
                    }

                    // send the response
                    out.println("Message received: " + response + " from Balancer at " + domain + ":" + port);

                    // update the semaphore
                    semaphore = (semaphore + 1) % denominator;
                } catch (IOException e) {
                    System.out.println("Error in balancer communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
