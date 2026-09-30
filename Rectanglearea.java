import java.util.Scanner;

public class Rectanglearea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for length
        System.out.print("Enter the length of the rectangle: ");
        double length = scanner.nextDouble();

        // Prompt for width
        System.out.print("Enter the width of the rectangle: ");
        double width = scanner.nextDouble();

        // Calculate area
        double area = length * width;

        // Display result
        System.out.println("The area of the rectangle is: " + area);

        scanner.close();
    }
}

