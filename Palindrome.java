import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder reversedStr = new StringBuilder(str).reverse();
        
        if (str.equals(reversedStr.toString())) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    
}