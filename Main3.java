
import java.util.*;
public class Main3 {
    public static void main(String [] ags){
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int Com_count=0;
        int Pri_count=0;
        for (; n > 0 ; n/=10) {
          int count=0;
            for (int i =1 ; i <=n ; i++) {
            if (n%i==0)
                count++;
        }
        if(count==2){
            Com_count++;
        }else{

        }
        }
    }
}
