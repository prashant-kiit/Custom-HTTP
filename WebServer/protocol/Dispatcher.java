package WebServer.protocol;

public class Dispatcher {
    private DispatcherHandler dispatcherHandler;

    public Dispatcher(RouterHandler routerHandler, MainTaskQueue mainTaskQueue, ControllerExecutor controllerExecutor) {
        this.dispatcherHandler = new DispatcherHandler(routerHandler, mainTaskQueue, controllerExecutor);
    }

    public void listen() {
        new Thread(dispatcherHandler).start();
    };
}
