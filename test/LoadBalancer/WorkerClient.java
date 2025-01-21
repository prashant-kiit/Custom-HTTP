package test.LoadBalancer;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerClient implements Runnable {
    private String serverAddress = "localhost";
    private Integer port = 8080;
    private FileWriter dataWriter;
    private FileWriter errorWriter;
    private Integer requestNo;

    public WorkerClient(FileWriter dataWriter, FileWriter errorWriter, Integer requestNo) {
        this.dataWriter = dataWriter;
        this.errorWriter = errorWriter;
        this.requestNo = requestNo;
    }

    @Override
    public void run() {
        // while (true) {
        try (Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("Hello from Client!");

            String response = in.readLine();
            System.out.println("Received: " + requestNo + " - " + response);

            try {
                String content = "Received: " + response;
                dataWriter.append(content + "\n");
            } catch (IOException ex) {
                System.err.println("File writing error occurred: " + ex.getMessage());
            }

        } catch (IOException e) {
            try {
                String content = "Error in client communication: " + e;
                errorWriter.append(content + "\n");
            } catch (IOException ex) {
                System.err.println("File writing error occurred: " + ex.getMessage());
            }
        }
        // }
    }

}
