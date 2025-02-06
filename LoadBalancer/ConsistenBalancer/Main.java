package ConsistenBalancer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Balancer balancer = new Balancer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a string (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            balancer.place(input);
        }

        scanner.close();
    }
}
