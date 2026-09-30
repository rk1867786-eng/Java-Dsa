import java.util.*;
public class Main1 {
    public static void main(String [] args )
    {
//        for (int i = 101; i <=200 ; i+=2)
//            System.out.println(i-100);
        Scanner sc =new Scanner(System.in);
            int c=0;
            int a=sc.nextInt();
////        int day1=sc.nextInt(a);
////        for(int day=2;day<=a;day++) {
////            int nxtday = sc.nextInt();
////                if(nxtday - day1 >=3){
////                    c++;}
////                day1=nxtday;
////            }
////        System.out.println("No. of Improvement Days = "+c);
        for (int i = 1; i <= a; i++) {
            if(a%i==0){
                c++;
            }
        }
        if (c==2){
            System.out.println(" "+a+" is a Prime ");
        }else{
            System.out.println(" "+ a +" not a prime Number");
        }
        sc.close();
    }
}
