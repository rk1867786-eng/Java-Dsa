import java.util.*;
public class Palindrome {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a = sc.nextInt();
        int palindrome=a;
        int r=0;
        while(a>0){
            int d=a%10;
            r=r*10+d;
            a/=10;
        }
        if(palindrome==r)
            System.out.println("IT is a Palindrome");
        else System.out.println("it is not a palindrome");
    }
}
