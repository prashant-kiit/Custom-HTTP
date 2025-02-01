package WebServer.protocol;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class DispatcherHandler implements Runnable {
    private RouterHandler routerHandler;
    private MainTaskQueue mainTaskQueue;
    private ControllerExecutor controllerExecutor;

    public DispatcherHandler(RouterHandler routerHandler, MainTaskQueue mainTaskQueue,
            ControllerExecutor controllerExecutor) {
        this.routerHandler = routerHandler;
        this.mainTaskQueue = mainTaskQueue;
        this.controllerExecutor = controllerExecutor;
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
            Route matchedRoute = routerHandler.getRoutes()
                    .stream()
                    .filter(route -> URLComponent.isSimilar(route, request))
                    .findFirst()
                    .orElse(null);

            // handled unmatched route request
            if (matchedRoute == null) {
                Response response = new Response().setCode(404).setMessage("Failure").setError("Route not found");
                try {
                    connector.sendResponse(response);
                } catch (IOException e) {
                    System.out.println("Dispatcher exception: " + e.getMessage());
                    e.printStackTrace();
                }
                return;
            }

            // use matched route to get contoller and generate reponse. all this will
            // happend in executor service (thread pool)

            controllerExecutor.exceute(matchedRoute, request, connector);
        }
    }
}
