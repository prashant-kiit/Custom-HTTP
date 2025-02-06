package InconsistentBalancer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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
            Thread server = new Thread(new Service(domain, port));
            server.start();
        }

        // run the event loop
        EventLoop eventLoop = new EventLoop(services);
        Thread eventLoopThread = new Thread(eventLoop);
        eventLoopThread.start();

        // run the balancer
        Balancer balancer = new Balancer("localhost", 8080);
        Thread balancerThread = new Thread(balancer);
        balancerThread.start();
    }
}
