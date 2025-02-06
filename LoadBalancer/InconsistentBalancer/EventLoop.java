package LoadBalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class EventLoop implements Runnable {
    private ArrayList<Config> services;
    private Integer semaphore;
    private Integer denominator;
    private Integer limitTime = 0;

    public EventLoop(ArrayList<Config> services) {
        this.services = services;
        this.semaphore = 0;
        this.denominator = services.size();
    }

    public EventLoop(ArrayList<Config> services, Integer limitTime) {
        this.services = services;
        this.semaphore = 0;
        this.denominator = services.size();
        this.limitTime = limitTime;
    }

    @Override
    public void run() {
        System.out.println("Event loop is running and polling macro task queue...");
        while (true) {
            try {
                // short polling on macro task queue
                MacroTaskQueue macroTaskQueue = MacroTaskQueue.getInstance();
                Socket socket = macroTaskQueue.pollSocket();
                if (socket == null) {
                    continue;
                }
                System.out.println("Socket polled from macro task queue: " + socket);

                // receive the message
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String message = in.readLine();

                // load balancing starts here using round robin
                String response = "uninitialized variable";
                String subdomain = services.get(semaphore).getDomain();
                Integer subport = services.get(semaphore).getPort();
                System.out.println("New client directed to " + subdomain + ":" + subport);

                try (Socket subsocket = new Socket(subdomain, subport);
                        PrintWriter subout = new PrintWriter(subsocket.getOutputStream(), true);
                        BufferedReader subin = new BufferedReader(
                                new InputStreamReader(subsocket.getInputStream()))) {

                    subout.println(message);
                    response = subin.readLine();
                } catch (IOException e) {
                    System.out.println("Error in Load Balancing: " + e.getMessage());
                }

                // send the response
                out.println("Message received: " + response + " from Balancer at " + socket.getLocalPort());

                // close the connection
                in.close();
                out.close();
                socket.close();

                // update the semaphore
                semaphore = (semaphore + 1) % denominator;

                // rate limiter by slowing down
                Thread.sleep(limitTime);
            } catch (IOException | InterruptedException e) {
                System.out.println("Error in event loop: " + e.getMessage());
            }
        }
    }
}
