import java.util.*;

public class NewYearGarland {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();  // number of test cases

        while (t-- > 0) {
            long r = sc.nextLong();
            long g = sc.nextLong();
            long b = sc.nextLong();

            long maxColor = Math.max(r, Math.max(g, b));
            long sumOther = r + g + b - maxColor;

            if (maxColor <= sumOther + 1) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        sc.close();
    }
}
