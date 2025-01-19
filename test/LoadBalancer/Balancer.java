package test.LoadBalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Balancer implements Runnable {
    private String name;
    private Integer port;
    private Integer semaphore = 1;

    public Balancer(String name, Integer port) {
        this.name = name;
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Balancer is listening on port " + port);
            while (true) {
                try (Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("New client connected to Balancer with semaphore " + semaphore);

                    String message = in.readLine();

                    String response = "uninitialized variable";
                    if (semaphore == 1) {
                        try (Socket socket1 = new Socket("localhost", 8080);
                                PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
                                BufferedReader in1 = new BufferedReader(
                                        new InputStreamReader(socket1.getInputStream()))) {

                            System.out.println("New client directed to Service 1");
                            out1.println(message);
                            response = in1.readLine();
                        } catch (IOException e) {
                            System.out.println("Error in Service 1 communication: " + e.getMessage());
                        }
                    }

                    if (semaphore == -1) {
                        try (Socket socket2 = new Socket("localhost", 8081);
                                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
                                BufferedReader in2 = new BufferedReader(
                                        new InputStreamReader(socket2.getInputStream()))) {

                            System.out.println("New client directed to Service 2");
                            out2.println(message);
                            response = in2.readLine();
                        } catch (IOException e) {
                            System.out.println("Error in Service 2 communication: " + e.getMessage());
                        }
                    }

                    out.println("Message received: " + response + " from " + name);

                    semaphore = (semaphore * -1);
                } catch (IOException e) {
                    System.out.println("Error in balancer communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
