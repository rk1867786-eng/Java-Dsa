import java.util.*;
public class Reverse {
    public static void  main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int rev=0;
        while(a>0){
            int d=a%10;

            rev=rev*10+d;
            a/=10;
        }
        System.out.println(rev);
        sc.close();
    }
}
