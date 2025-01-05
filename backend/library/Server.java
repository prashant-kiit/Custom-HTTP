package backend.library;

import java.net.ServerSocket;
import java.net.Socket;

import backend.ENV;

public class Server implements Runnable {
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(ENV.PORT)) {
            System.out.println("Server is listening on port " + ENV.PORT + " at domain " + ENV.DOMAIN);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                Channel channel = new Channel(socket);
                new Thread(channel).start();
            }
        } catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
