package protocol;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class Route {
    private String method;
    private String path;
    private Queue<Connector> controllerQueue;
    private Function<Request, Response> controller;

    public Route(String method, String path, Function<Request, Response> controller) {
        this.method = method;
        this.path = path;
        this.controller = controller;
        this.controllerQueue = new LinkedList<Connector>();
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public synchronized Queue<Connector> getControllerQueue() {
        return controllerQueue;
    }

    public Function<Request, Response> getController() {
        return controller;
    }
}
