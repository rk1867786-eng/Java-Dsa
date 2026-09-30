import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
public class StringArray {
    public static  void main (String[] args){
        Scanner sc = new Scanner(System.in);
        String a[]={"Ram","sita","lakshman","hanuman"};

        System.out.println("Enter String wants to search : ");
        String z =sc.nextLine();
        int x=0;
        for (int i=0;i<a.length;i++) {
            if (a[i].equalsIgnoreCase(z))
                x++;
        }
         if(x>0){
            System.out.println("String found !");
        }else {
            System.out.println("String not found!");
        }

    }
}
