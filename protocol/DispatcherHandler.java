package protocol;

import java.io.IOException;

public class DispatcherHandler implements Runnable {
    private RouterHandler routerHandler;
    private MainTaskQueue mainTaskQueue;

    public DispatcherHandler(RouterHandler routerHandler, MainTaskQueue mainTaskQueue) {
        this.routerHandler = routerHandler;
        this.mainTaskQueue = mainTaskQueue;
    }

    @Override
    public void run() {
        System.out.println("Dispatcher is running");
        while (true) {
            // pull the request from main task queue
            Connector connector = this.mainTaskQueue.getQueue().poll();
            if (connector == null)
                continue;
            Request request = connector.getRequest();

            // find matched and unmatched route request
            Route result = routerHandler.getRoutes()
                    .stream()
                    .filter(route -> URLComponent.isSimilar(route, request)).findFirst().orElse(null);

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
        }
    }

}
