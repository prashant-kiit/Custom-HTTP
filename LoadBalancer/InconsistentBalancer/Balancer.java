package LoadBalancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Balancer implements Runnable {
    private String domain;
    private Integer port;
    private Integer limitSize = 500000000;

    public Balancer(String domain, Integer port) {
        this.domain = domain;
        this.port = port;
    }

    public Balancer(String domain, Integer port, Integer limitSize) {
        this.domain = domain;
        this.port = port;
        this.limitSize = limitSize;
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

                    MacroTaskQueue macroTaskQueue = MacroTaskQueue.getInstance();

                    // rate limiter by rejection/ignoring out
                    Integer length = macroTaskQueue.getLength();
                    if (length >= limitSize) {
                        socket.close();
                        continue;
                    }

                    // queue the socket in macrotaskqueue
                    macroTaskQueue.addSocket(socket);
                } catch (IOException | InterruptedException e) {
                    System.out.println("Error in balancer communication: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
