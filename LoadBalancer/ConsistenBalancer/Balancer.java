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
        if (semaphore == 15) {
            circle.get(semaphore % 8).setIsActive(false);
        }

        Boolean isActive = circle.get(semaphore % 8).getIsActive();

        System.out.println("semaphore = " + semaphore);
        System.out.println("isActive = " + isActive);

        if (isActive) {
            circle.get(semaphore % 8).getQueue().add(input);
        } else {
            // consistent hashing mechanism
            Integer innerSemaphore = semaphore % 8;

            while (true) {
                Boolean innerIsActive = circle.get(innerSemaphore).getIsActive();
                if (innerIsActive) {
                    circle.get(innerSemaphore).getQueue().add(input);
                    break;
                }
                innerSemaphore = (innerSemaphore + 1) % 8;
            }

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
// drift the request to the next nearest active server