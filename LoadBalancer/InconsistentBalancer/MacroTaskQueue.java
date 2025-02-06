
package LoadBalancer;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class MacroTaskQueue {
    private static MacroTaskQueue instance;
    private Queue<Socket> activeQueue;
    private Queue<Socket> passiveQueue;
    private Queue<Socket> queue1;
    private Queue<Socket> queue2;

    private MacroTaskQueue() {
        queue1 = new LinkedList<Socket>();
        queue2 = new LinkedList<Socket>();
        activeQueue = queue1;
        passiveQueue = queue2;
    }

    private void switchQueue() throws InterruptedException {
        if (activeQueue.size() >= 1000) {
            Queue<Socket> temp = passiveQueue;
            passiveQueue = activeQueue;
            activeQueue = temp;
            // Thread.sleep(2500);
            System.out.println("--------------------Switched macro task queue -----------------");
        }
    }

    public synchronized static MacroTaskQueue getInstance() {
        if (instance == null) {
            instance = new MacroTaskQueue();
        }
        return instance;
    }

    public synchronized void addSocket(Socket socket) throws InterruptedException {
        // stop-the-world buffer context switch
        switchQueue();
        activeQueue.add(socket);
    }

    public synchronized Socket pollSocket() {
        return passiveQueue.poll();
    }

    public synchronized Integer getLength() {
        return (activeQueue.size() + passiveQueue.size()) / 2;
    }
}
