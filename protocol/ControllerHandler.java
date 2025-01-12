package protocol;

import java.io.IOException;

public class ControllerHandler implements Runnable {
    private Route route;

    public ControllerHandler(Route route) {
        this.route = route;
    }

    @Override
    public void run() {
        System.out.println("Controller is running");
        while (true) {
            // pull the request from controller queue
            Connector connector = this.route.getControllerQueue().poll();
            if (connector == null)
                continue;
            Request request = connector.getRequest();

            // execute the controller function
            Response response = this.route.getController().apply(request);
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
    }

}
