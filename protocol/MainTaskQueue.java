package protocol;

import java.util.Queue;
import java.util.LinkedList;

public class MainTaskQueue {
    private Queue<Connector> queue;

    public MainTaskQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized Queue<Connector> getQueue() {
        return this.queue;
    }

    public synchronized void addConnector(Connector connector) {
        this.queue.add(connector);
        System.out.println("Connector added to main task queue : connector = " + connector);
    }
}
