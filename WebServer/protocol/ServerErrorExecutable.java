package WebServer.protocol;

import java.io.IOException;

public class ServerErrorExecutable implements Runnable {
    private Connector connector;

    public ServerErrorExecutable(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void run() {
        try {
            Response response = new Response().setCode(404).setMessage("Failure").setError("Route not found");
            connector.sendResponse(response);
        } catch (IOException e) {
            System.out.println("Dispatcher exception: " + e.getMessage());
            e.printStackTrace();
        }
        return;
    }

}
