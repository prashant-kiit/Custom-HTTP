package WebServer.protocol;

import java.io.IOException;

public class Server implements Runnable {
    private Integer port;
    private String domain;
    private MainTaskQueue mainTaskQueue;

    public Server(String domain, Integer port, MainTaskQueue mainTaskQueue) throws IOException {
        this.domain = domain;
        this.port = port;
        this.mainTaskQueue = mainTaskQueue;
    }

    @Override
    public void run() {
        try {
            ServerConnector serverConnector = new ServerConnector(port);
            System.out.println("Server is listening on port " + port + " at domain " + domain);
            while (true) {
                Connector connector = serverConnector.accept();
                System.out.println("New client connected");
                connector.receiveRequest();
                System.out.println("Connector received a request : connector = " + connector);
                this.mainTaskQueue.addConnector(connector);
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
