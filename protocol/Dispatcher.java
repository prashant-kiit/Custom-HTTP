package protocol;

public class Dispatcher {
    public void listen() {
        new Thread(new DispatcherHandler()).start();
    };
}
