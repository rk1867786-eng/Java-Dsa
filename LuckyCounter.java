import java.util.*;
public class LuckyCounter {
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int a=sc.nextInt();
    int count=0;
    do{
        int d=a%10;
        if(d==7){
            count++;
        }
    }while(a>0);
        System.out.println(""+count);
        sc.close();
    }}
