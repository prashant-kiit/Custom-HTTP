package protocol;

public class Dispatcher {
    private DispatcherHandler dispatcherHandler;

    public Dispatcher(RouterHandler routerHandler) {
        dispatcherHandler = new DispatcherHandler(routerHandler);
    }

    public void listen() {
        new Thread(dispatcherHandler).start();
    };
}
