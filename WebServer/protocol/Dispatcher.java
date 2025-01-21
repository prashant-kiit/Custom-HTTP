package WebServer.protocol;

public class Dispatcher {
    private DispatcherHandler dispatcherHandler;

    public Dispatcher(RouterHandler routerHandler, MainTaskQueue mainTaskQueue) {
        dispatcherHandler = new DispatcherHandler(routerHandler, mainTaskQueue);
    }

    public void listen() {
        new Thread(dispatcherHandler).start();
    };
}
