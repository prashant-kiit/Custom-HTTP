package WebServer.protocol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerExecutor {
    private ExecutorService executor;

    public ControllerExecutor() {
        this.executor = Executors.newFixedThreadPool(4);
    }

    public void exceute(Route matchedRoute, Request request, Connector connector) {
        ControllerExecutable controllerExecutable = new ControllerExecutable(matchedRoute, request, connector);
        executor.submit(controllerExecutable);
    }

    public void exceuteServerErrorHandler(Connector connector) {
        ServerErrorExecutable serverErrorExecutable = new ServerErrorExecutable(connector);
        executor.submit(serverErrorExecutable);
    }
}
