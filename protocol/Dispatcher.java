package protocol;

import java.io.IOException;

public class Dispatcher {
    public void listen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // pull the request from main task queue
                    Connector connector = MainTaskQueue.getInstance().getQueue().poll();
                    Request request = connector.getRequest();

                    // find matched and unmatched route request
                    Route result = RouterHandler.getRoutes()
                            .stream()
                            .filter(route -> URLComponent.isSimilar(route, request)).findFirst().orElse(null);

                    // handled unmatched route request
                    if (result == null) {
                        connector.getResponse().setCode(404).setMessage("Failure").setError("Route not found");
                        try {
                            connector.sendResponse(connector.getResponse());
                        } catch (IOException e) {
                            System.out.println("Dispatcher exception: " + e.getMessage());
                            e.printStackTrace();
                        }
                        return;
                    }

                    // handled matched route request
                    result.getControllerQueue().add(connector);
                }
            }
        }).start();
    };
}
