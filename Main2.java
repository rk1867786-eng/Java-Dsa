import java.sql.SQLOutput;
import java.util.*;
public class Main2 {
    public static void main(String [] arg){
//        Scanner sc=new Scanner(System.in);
//        int a = sc.nextInt();
        for(int i = 1;i<=100;i++)
        {
            int c = 0;
            for(int j = 1;j<=i;j++)
            {
                if(i%j==0)
                    c++;
            }
            if(c>2)
                System.out.println("No. is composite = "+i);
        }
        //sc.close();
    }
}
