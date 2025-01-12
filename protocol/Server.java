package protocol;

import java.io.IOException;

public class Server implements Runnable {
    private Integer port;
    private String domain;

    public Server(String domain, Integer port) throws IOException {
        this.domain = domain;
        this.port = port;
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
                MainTaskQueue.getInstance().addConnector(connector);
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
