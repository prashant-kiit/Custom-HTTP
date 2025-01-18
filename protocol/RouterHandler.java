package protocol;

import java.util.ArrayList;
import java.util.function.Function;

public class RouterHandler {
    private ArrayList<Route> routes;

    public RouterHandler() {
        routes = new ArrayList<Route>();
    }

    public synchronized void insertRoute(String routeName, Function<Request, Response> controller) {
        String[] keyParts = routeName.split("=");
        String keyMethod = keyParts[0];
        String keyPath = keyParts[1];
        // create route
        Route route = new Route(keyMethod, keyPath, controller);
        // start controller thread
        new Thread(new ControllerHandler(route)).start();
        // add route with controller and respective queue
        this.routes.add(route);

        System.out.println("Route added to router : routes = " + routes);
    }

    public synchronized ArrayList<Route> getRoutes() {
        return this.routes;
    }
}
