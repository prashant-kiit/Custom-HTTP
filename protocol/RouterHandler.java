package protocol;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RouterHandler {
    protected Map<Map<String, String>, Function<Request, Response>> routes = new HashMap<Map<String, String>, Function<Request, Response>>();

    protected void insertRoute(String key, Function<Request, Response> value) {
        String[] keyParts = key.split("=");
        String keyMethod = keyParts[0];
        String keyPath = keyParts[1];
        Map<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("method", keyMethod);
        keyMap.put("path", keyPath);
        this.routes.put(keyMap, value);
    }

    public Map<Map<String, String>, Function<Request, Response>> getRoutes() {
        return this.routes;
    }
}
