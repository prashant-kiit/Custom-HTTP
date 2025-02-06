package ConsistenBalancer;

import java.util.ArrayList;
import java.util.List;

public class Balancer {
    private List<String> circle = new ArrayList<String>();

    public void place(String input) {
        circle.add(input);
        System.out.println("Circle status: " + circle);
    }
}
