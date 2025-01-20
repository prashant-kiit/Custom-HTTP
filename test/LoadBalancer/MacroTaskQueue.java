
package test.LoadBalancer;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class MacroTaskQueue {
    private static MacroTaskQueue instance;
    private Queue<Socket> queue;

    private MacroTaskQueue() {
        queue = new LinkedList<Socket>();
    }

    public synchronized static MacroTaskQueue getInstance() {
        if (instance == null) {
            instance = new MacroTaskQueue();
        }
        return instance;
    }

    public synchronized void addSocket(Socket socket) {
        queue.add(socket);
    }

    public synchronized Socket pollSocket() {
        return queue.poll();
    }
}
