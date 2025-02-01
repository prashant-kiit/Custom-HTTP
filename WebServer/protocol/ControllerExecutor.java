package WebServer.protocol;

import java.util.concurrent.ExecutorService;

public class ControllerExecutor {
    public static void exceute(ExecutorService executor, Route matchedRoute, Request request, Connector connector) {
        ControllerExecutable controllerExecutable = new ControllerExecutable(matchedRoute, request, connector);
        executor.submit(controllerExecutable);
    }
}
