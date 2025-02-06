package ConsistenBalancer;

import java.util.ArrayList;
import java.util.List;

public class Balancer {
    private List<Server> circle = new ArrayList<Server>();
    private Integer semaphore = 0;

    public Balancer() {
        for (Integer i = 0; i < 8; i++) {
            Server server = new Server(true);
            circle.add(server);
        }
    }

    public void place(String input) {
        Boolean isActive = circle.get(semaphore).getIsActive();
        if (isActive) {
            circle.get(semaphore).getQueue().add(input);
        }
        semaphore = (semaphore + 1) % 8;
        printCircleStatus();
    }

    private void printCircleStatus() {
        System.out.println("Status\n");
        for (Server server : circle) {
            System.out.println(server.getQueue());
        }
    }
}
