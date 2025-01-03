package backend.src.application;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import library.Request;
import library.Response;

public class Route {
    private Map<String, Function<Request, Response>> routes;

    public Route() {
        this.routes = new HashMap<String, Function<Request, Response>>();
        this.routes.put("GET:users", Controller.getUsers);
    }

    public Map<String, Function<Request, Response>> getRoutes() {
        return this.routes;
    }
}
