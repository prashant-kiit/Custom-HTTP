package backend.src.application;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import protocol.Request;
import protocol.Response;

public class Route {
    private Map<String, Function<Request, Response>> routes;

    public Route() {
        this.routes = new HashMap<String, Function<Request, Response>>();
        this.routes.put("GET=users", Controller.getUsers);
        this.routes.put("GET=users/:name", Controller.getUserById);
        this.routes.put("POST=user", Controller.postUsers);
    }

    public Map<String, Function<Request, Response>> getRoutes() {
        return this.routes;
    }
}
