package test.LoadBalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerClient implements Runnable {
    private String serverAddress = "localhost";
    private Integer port = 8080;

    @Override
    public void run() {
        // while (true) {
        try (Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("Hello from Client!");

            String response = in.readLine();
            System.out.println("Received: " + response);

        } catch (IOException e) {
            System.out.println("Error in client communication: " + e.getMessage());
        }
        // }
    }

}
