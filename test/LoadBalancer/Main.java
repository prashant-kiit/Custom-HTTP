package test.LoadBalancer;

public class Main {
    public static void main(String[] args) {
        Balancer balancer = new Balancer("Balancer", 8082);
        Service service1 = new Service("Server 1", 8080);
        Service service2 = new Service("Server 2", 8081);
        new Thread(balancer).start();
        new Thread(service1).start();
        new Thread(service2).start();
    }
}
