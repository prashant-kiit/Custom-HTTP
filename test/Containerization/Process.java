package test.Containerization;

public class Process implements Runnable {
    private String name;

    public Process(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Resource resource = Resource.getInstance();
        System.out.println("In Thread = " + name + " and Resource = " + resource);
    }
}
