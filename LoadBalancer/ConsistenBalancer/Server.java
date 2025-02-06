package ConsistenBalancer;

import java.util.LinkedList;
import java.util.Queue;

public class Server {
    private Boolean isActive;
    private Queue<String> queue = new LinkedList<>();

    public Server(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
