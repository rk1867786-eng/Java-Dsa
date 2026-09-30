import java.util.Scanner;

public class factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user
        System.out.print("Enter a non-negative integer: ");
        int number = scanner.nextInt();

        // Check for valid input
        if (number < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
        } else {
            long factorial = 1;

            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }

            System.out.println("Factorial of " + number + " is: " + factorial);
        }

        scanner.close();
    }
}

