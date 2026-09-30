import java.util.*;
public class Ao {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
      
      String s1 = sc.next().toLowerCase();
      for(int i=0; i<s1.length();i++) {
         char var = s1.charAt(i);
         if (var == 'a' || var == 'e' || var == 'i' || var == 'o' || var=='y'|| var=='Y'||var == 'u' || var == 'A' || var == 'E' || var == 'I' || var == 'O' || var == 'U') {
            continue;
         }else{
            System.out.print("." +var);
         }
      }
   }
    }