import java.util.Scanner;
public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder reversedStr = new StringBuilder(str).reverse();
        System.out.println(reversedStr.toString());
    }
}