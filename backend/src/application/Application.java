package backend.src.application;

import backend.src.TableOfQueues;
import library.Request;

public class Application implements Runnable {
    private TableOfQueues tableOfQueues;
    private Route route;

    public Application(TableOfQueues tableOfQueues) {
        this.tableOfQueues = tableOfQueues;
        this.route = new Route();
    }

    @Override
    public void run() {
        while (true) {
            route.getRoutes().keySet().stream().forEach(methodAndPath -> {
                Request request = tableOfQueues.read(methodAndPath);
                if (request != null) {
                    route.getRoutes().get(methodAndPath).apply(request);
                }
            });
        }
    }
}
