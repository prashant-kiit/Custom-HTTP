package protocol;

public class Dispatcher {
    private Integer holdTime;

    public Dispatcher(Integer holdTime) {
        this.holdTime = holdTime;
    }

    public void listen() {
        new Thread(new DispatcherHandler(holdTime)).start();
    };
}
