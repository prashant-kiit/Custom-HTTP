package protocol;

import java.io.IOException;

public class DispatcherHandler implements Runnable {
    @Override
    public void run() {
        System.out.println("Dispatcher is running");
        while (true) {
            // pull the request from main task queue
            Connector connector = MainTaskQueue.getInstance().getQueue().poll();
            if (connector == null)
                continue;
            Request request = connector.getRequest();
            // System.out.println("Dispatcher receives a request : connector = " +
            // connector);
            // System.out.println("Dispatcher receives a request : request = " + request);
            // System.out.println("Dispatcher receives a request : route = " +
            // RouterHandler.getInstance().getRoutes());

            // find matched and unmatched route request
            Route result = RouterHandler.getInstance().getRoutes()
                    .stream()
                    .filter(route -> URLComponent.isSimilar(route, request)).findFirst().orElse(null);

            // System.out.println("Dispatcher receives a request : result = " + result);
            // handled unmatched route request
            if (result == null) {
                Response response = new Response().setCode(404).setMessage("Failure").setError("Route not found");
                try {
                    connector.sendResponse(response);
                } catch (IOException e) {
                    System.out.println("Dispatcher exception: " + e.getMessage());
                    e.printStackTrace();
                }
                return;
            }

            // handled matched route request
            result.getControllerQueue().add(connector);
            // System.out.println("Dispatcher receives a request : controller queue = "
            // + result.getControllerQueue().peek().getRequest());
        }
    }

}
