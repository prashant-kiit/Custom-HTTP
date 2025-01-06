package backend.src.application;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import protocol.Request;
import protocol.Response;

public class Route {
    private Map<Map<String, String>, Function<Request, Response>> routes;

    public Route() {
        this.routes = new HashMap<Map<String, String>, Function<Request, Response>>();
        this.insertRoute("GET=users", Controller.getUsers);
        this.insertRoute("GET=users/:name", Controller.getUserById);
        this.insertRoute("POST=user", Controller.postUsers);
    }

    public Map<Map<String, String>, Function<Request, Response>> getRoutes() {
        return this.routes;
    }

    public void insertRoute(String key, Function<Request, Response> value) {
        String[] keyParts = key.split("=");
        String keyMethod = keyParts[0];
        String keyPath = keyParts[1];
        Map<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("method", keyMethod);
        keyMap.put("path", keyPath);
        this.routes.put(keyMap, value);
    }
}
