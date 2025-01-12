package protocol;

import java.util.ArrayList;
import java.util.function.Function;

public class RouterHandler {
    private static ArrayList<Route> routes = new ArrayList<Route>();

    public static void insertRoute(String routeName, Function<Request, Response> controller) {
        String[] keyParts = routeName.split("=");
        String keyMethod = keyParts[0];
        String keyPath = keyParts[1];
        // create route
        Route route = new Route(keyMethod, keyPath, controller);
        // start controller thread
        new Thread(new ControllerHandler(route)).start();
        // add route with controller and respective queue
        routes.add(route);
    }

    public static ArrayList<Route> getRoutes() {
        return routes;
    }
}
