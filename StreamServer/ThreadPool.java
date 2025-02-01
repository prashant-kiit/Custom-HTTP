package StreamServer;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPool {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks for execution
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate a time-consuming task
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Gracefully shut down the executor
        executor.shutdown();
    }
}
