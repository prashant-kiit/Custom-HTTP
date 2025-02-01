package WebServer.protocol;

import java.util.concurrent.ExecutorService;

public class Dispatcher {
    private DispatcherHandler dispatcherHandler;

    public Dispatcher(RouterHandler routerHandler, MainTaskQueue mainTaskQueue, ExecutorService executor) {
        dispatcherHandler = new DispatcherHandler(routerHandler, mainTaskQueue, executor);
    }

    public void listen() {
        new Thread(dispatcherHandler).start();
    };
}
