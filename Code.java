// public class CodeF2 {
    
// }
import java.util.Scanner;

public class Code {
    public static void main(String[] args) {
        // Scanner ka use karke input lenge
        Scanner sc = new Scanner(System.in);

        // n: doston ki sankhya, h: fence ki height
        int n = sc.nextInt();
        int h = sc.nextInt();

        int h = 0;

        for (int i = 0; i < n; i++) {
            int e = sc.nextInt();

            // Agar height fence se badi hai, toh bend hona padega (width = 2)
            // Warna normal khade rahenge (width = 1)
            if (e> h) {
                h += 2;
            } else {
                h += 1;
            }
        }

        // Final min width print karenge
        System.out.println(h);

        sc.close();
    }
}