import java.util.Scanner;

public class FastCash {

    // Simulated ATM account details
    static String userId = "user123";
    static String pin = "1234";
    static double balance = 25000.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Welcome to ATM Simulator =====");
        System.out.print("Enter User ID: ");
        String inputUserId = sc.nextLine();

        System.out.print("Enter PIN: ");
        String inputPin = sc.nextLine();

        // Authentication check
        if (inputUserId.equals(userId) && inputPin.equals(pin)) {
            System.out.println("\nLogin successful!");
            showMenu(sc);
        } else {
            System.out.println("Invalid User ID or PIN! Access Denied.");
        }

        sc.close();
    }

    // Function to display fast cash menu
    static void showMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n===== FAST CASH MENU =====");
            System.out.println("1. ₹500");
            System.out.println("2. ₹1000");
            System.out.println("3. ₹2000");
            System.out.println("4. ₹5000");
            System.out.println("5. ₹10000");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    withdrawAmount(500);
                    break;
                case 2:
                    withdrawAmount(1000);
                    break;
                case 3:
                    withdrawAmount(2000);
                    break;
                case 4:
                    withdrawAmount(5000);
                    break;
                case 5:
                    withdrawAmount(10000);
                    break;
                case 6:
                    checkBalance();
                    break;
                case 7:
                    System.out.println("Thank you for using ATM Simulator!");
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (choice != 7);
    }

    // Function to handle withdrawal
    static void withdrawAmount(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Transaction Successful!");
            System.out.println("You have withdrawn ₹" + amount);
            System.out.println("Remaining Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // Function to check balance
    static void checkBalance() {
        System.out.println("Your Current Balance: ₹" + balance);
    }
}

