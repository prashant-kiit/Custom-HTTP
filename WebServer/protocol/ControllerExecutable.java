package WebServer.protocol;

import java.io.IOException;

public class ControllerExecutable implements Runnable {

    private Route matchedRoute;
    private Request request;
    private Connector connector;

    public ControllerExecutable(Route matchedRoute, Request request, Connector connector) {
        this.matchedRoute = matchedRoute;
        this.request = request;
        this.connector = connector;
    }

    @Override
    public void run() {
        Response response = matchedRoute.getController().apply(request);
        try {
            // send response
            connector.sendResponse(response);

            // close connector
            connector.close();
        } catch (IOException e) {
            System.out.println("RouterHandler exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendServerError() {

    }

}
