package WebServer.protocol;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import WebServer.backend.src.application.RouteBuilder;

public class Container implements Runnable {
    private String name;
    private String domain;
    private Integer port;

    public Container(String name, String domain, Integer port) {
        this.name = name;
        this.domain = domain;
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("Running = " + name);

        RouterHandler routerHandler = new RouterHandler();
        MainTaskQueue mainTaskQueue = new MainTaskQueue();
        ControllerExecutor controllerExecutor = new ControllerExecutor();


        // starts controller thread
        RouteBuilder routeBuilder = new RouteBuilder(routerHandler);
        routeBuilder.listen();

        // starts dispacher thread
        Dispatcher dispatcher = new Dispatcher(routerHandler, mainTaskQueue, controllerExecutor);
        dispatcher.listen();

        // starts server thread
        Server server;
        try {
            server = new Server(domain, port, mainTaskQueue);
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
