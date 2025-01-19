package test.LoadBalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // configure and register the services
        ArrayList<Config> services = new ArrayList<Config>();
        services.add(new Config("localhost", 8081));
        services.add(new Config("localhost", 8082));
        services.add(new Config("localhost", 8083));
        services.add(new Config("localhost", 8084));

        for (Config service : services) {
            // get the service name and port
            String domain = service.getDomain();
            Integer port = service.getPort();

            // run the server
            new Thread(new Service(domain, port)).start();
        }

        // run the balancer
        Balancer balancer = new Balancer("localhost", 8080, services);
        new Thread(balancer).start();
    }
}
