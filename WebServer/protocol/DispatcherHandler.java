package WebServer.protocol;

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
            handleRequest(connector, request, matchedRoute);
        }
    }

    private void handleRequest(Connector connector, Request request, Route matchedRoute) {
        if (matchedRoute == null) {
            controllerExecutor.exceuteServerErrorHandler(connector);
        }
        // use matched route to get controller and generate response. all this will
        // happen in executor service (thread pool)
        controllerExecutor.exceute(matchedRoute, request, connector);
    }
}
