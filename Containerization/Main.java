package Containerization;

public class Main {
    public static void main(String[] args) {
        new Thread(new Process("Thread 1")).start();
        new Thread(new Process("Thread 2")).start();
    }
}
