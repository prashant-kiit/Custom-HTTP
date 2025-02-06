package ConsistenBalancer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create Scanner object for reading input
        Scanner scanner = new Scanner(System.in);
        
        // Infinite loop to continuously read input
        while (true) {
            System.out.print("Enter a string (or 'exit' to quit): ");
            String input = scanner.nextLine();
            
            // Optional: add exit condition
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            
            // Process the input string here
            System.out.println("You entered: " + input);
        }
        
        // Close the scanner
        scanner.close();
    }
}
