package ConsistenBalancer;

import java.util.ArrayList;
import java.util.List;

public class Balancer {
    private List<Server> circle = new ArrayList<Server>();
    private Integer semaphore = 0;
    private Integer round = 0;

    public Balancer() {
        for (Integer i = 0; i < 8; i++) {
            Server server = new Server(true);
            circle.add(server);
        }
    }

    public void place(String input) {
        System.out.println("semaphore = " + semaphore);
        if (semaphore == 12) {
            circle.get(semaphore % 8).setIsActive(false);
        }

        Boolean isActive = circle.get(semaphore % 8).getIsActive();
        System.out.println("isActive = " + isActive);
        if (isActive) {
            circle.get(semaphore % 8).getQueue().add(input);
        }

        semaphore++;
        printCircleStatus();
    }

    private void printCircleStatus() {
        System.out.println("Status\n");
        for (Server server : circle) {
            System.out.println(server.getQueue());
        }
    }
}

// problems
// request ignored