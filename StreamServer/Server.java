package StreamServer;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is running...");

        try (Socket socket = serverSocket.accept();
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true)) {

            System.out.println("Client connected");

            // Streaming numbers 1 to 5
            IntStream.rangeClosed(1, 5)
                    .peek(i -> {
                        writer.println("Streaming Data: " + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    })
                    .forEach(i -> System.out.println("Sent: Streaming Data " + i));

            writer.println("END"); // Indicate the end of the stream
        }

        serverSocket.close();
    }
}
