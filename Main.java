import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        
        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int maxLen = 1;
        int currLen = 1;
        
     
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                currLen++;
            } else {
                currLen = 1;
            }
            
            if (currLen > maxLen) {
                maxLen = currLen;
            }
        }
        
        System.out.println(maxLen);
        sc.close();
    }
}