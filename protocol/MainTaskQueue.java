package protocol;

import java.util.Queue;
import java.util.LinkedList;

public class MainTaskQueue {
    private static MainTaskQueue instance;
    private Queue<Connector> queue = new LinkedList<>();

    private MainTaskQueue() {
    }

    public static synchronized MainTaskQueue getInstance() {
        if (instance == null) {
            instance = new MainTaskQueue();
        }
        return instance;
    }

    public synchronized Queue<Connector> getQueue() {
        return queue;
    }

    public synchronized void addConnector(Connector connector) {
        queue.add(connector);
        System.out.println("Connector added to main task queue : connector = " + connector);
    }
}
