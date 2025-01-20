package test.LoadBalancer;

public class Client {
    public static void main(String[] args) {
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
        new Thread(new WorkerClient()).start();
    }
}
