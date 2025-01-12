package protocol;

import java.io.IOException;

public class ControllerHandler implements Runnable {
    private Route route;

    public ControllerHandler(Route route) {
        this.route = route;
    }

    @Override
    public void run() {
        while (true) {
            Connector connector = route.getControllerQueue().poll();
            if (connector == null)
                return;
            Request request = connector.getRequest();
            Response response = route.getController().apply(request);
            try {
                connector.sendResponse(response);
            } catch (IOException e) {
                System.out.println("RouterHandler exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
